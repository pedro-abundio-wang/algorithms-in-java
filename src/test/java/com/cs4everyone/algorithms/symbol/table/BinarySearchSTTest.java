package com.cs4everyone.algorithms.symbol.table;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class BinarySearchSTTest {

  @Test
  public void test() {
    BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
    In in = new In("tinyST.txt");
    for (int i = 0; !in.isEmpty(); i++) {
      String key = in.readString();
      st.put(key, i);
    }

    for (String s : st.keys()) StdOut.println(s + " " + st.get(s));
  }
}
