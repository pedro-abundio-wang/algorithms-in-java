package com.cs4everyone.algorithms.symbol.table;

import com.cs4everyone.algorithms.utils.StdOut;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class BSTTest {

  @Test
  public void test() {
    String test = "S E A R C H E X A M P L E";
    String[] keys = test.split(StringUtils.SPACE);
    int n = keys.length;
    BST<String, Integer> bst = new BST<String, Integer>();
    for (int i = 0; i < n; i++) bst.put(keys[i], i);

    StdOut.println("size = " + bst.size());
    StdOut.println("min  = " + bst.min());
    StdOut.println("max  = " + bst.max());
    StdOut.println();

    // print keys in order using allKeys()
    StdOut.println("Testing keys()");
    StdOut.println("--------------------------------");
    for (String s : bst.keys()) StdOut.println(s + " " + bst.get(s));
    StdOut.println();

    // print keys in order using select
    StdOut.println("Testing select");
    StdOut.println("--------------------------------");
    for (int i = 0; i < bst.size(); i++) StdOut.println(i + " " + bst.select(i));
    StdOut.println();

    // test rank, floor, ceiling
    StdOut.println("key rank floor ceil");
    StdOut.println("-------------------------");
    for (char i = 'A'; i <= 'X'; i++) {
      String s = i + "";
      StdOut.printf("%2s %4d %4s %4s\n", s, bst.rank(s), bst.floor(s), bst.ceil(s));
    }
    StdOut.println();

    // test range search and range count
    String[] from = {"A", "Z", "X", "O", "B", "C"};
    String[] to = {"Z", "A", "X", "Z", "G", "L"};
    StdOut.println("range search");
    StdOut.println("-------------------");
    for (int i = 0; i < from.length; i++) {
      StdOut.printf("%s-%s (%2d) : ", from[i], to[i], bst.size(from[i], to[i]));
      for (String s : bst.keys(from[i], to[i])) StdOut.print(s + " ");
      StdOut.println();
    }
    StdOut.println();

    // delete the smallest keys
    for (int i = 0; i < bst.size() / 2; i++) {
      bst.deleteMin();
    }
    StdOut.println("After deleting the smallest " + bst.size() / 2 + " keys");
    StdOut.println("--------------------------------");
    for (String s : bst.keys()) StdOut.println(s + " " + bst.get(s));
    StdOut.println();

    // delete all the remaining keys
    while (!bst.isEmpty()) {
      bst.delete(bst.select(bst.size() / 2));
    }
    StdOut.println("After deleting the remaining keys");
    StdOut.println("--------------------------------");
    for (String s : bst.keys()) StdOut.println(s + " " + bst.get(s));
    StdOut.println();

    StdOut.println("After adding back the keys");
    StdOut.println("--------------------------------");
    for (int i = 0; i < n; i++) bst.put(keys[i], i);
    for (String s : bst.keys()) StdOut.println(s + " " + bst.get(s));
    StdOut.println();
  }
}
