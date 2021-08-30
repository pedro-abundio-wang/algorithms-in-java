package com.cs4everyone.algorithms.queue;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class QueueTest {

  @Test
  public void linkedQueueTest() {
    LinkedQueue<String> queue = new LinkedQueue<String>();
    In in = new In("tobe.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (!item.equals("-")) queue.enqueue(item);
      else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
    }
    StdOut.println("(" + queue.size() + " left on queue)");
  }

  @Test
  public void arrayQueueTest() {
    ResizingArrayQueue<String> queue = new ResizingArrayQueue<String>();
    In in = new In("tobe.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (!item.equals("-")) queue.enqueue(item);
      else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
    }
    StdOut.println("(" + queue.size() + " left on queue)");
  }

  @Test
  public void maxPQTest() {
    MaxPQ<String> priorityQueue = new MaxPQ<>();
    In in = new In("tinyPQ.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (!item.equals("-")) priorityQueue.insert(item);
      else if (!priorityQueue.isEmpty()) StdOut.print(priorityQueue.delMax() + " ");
    }
    StdOut.println("(" + priorityQueue.size() + " left on pq)");
  }
}
