package com.cs4everyone.algorithms.set;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class BagTest {

  @Test
  public void linkedBagTest() {
    Bag<String> bag = new Bag<>();
    In in = new In("tobe.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      bag.add(item);
    }

    StdOut.println("size of bag = " + bag.size());
    for (String s : bag) {
      StdOut.println(s);
    }
  }
}
