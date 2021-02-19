package com.cs4everyone.algorithms.sort;

import com.cs4everyone.algorithms.queue.LinkedQueue;
import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class ShuffleTest {

  @Test
  public void knuthTest() {
    // read in the data
    In in = new In("cards.txt");
    String[] cards = in.readAllStrings();

    // shuffle the array
    KnuthShuffle.shuffle(cards);

    // print results.
    for (int i = 0; i < cards.length; i++) StdOut.println(cards[i]);
  }
}
