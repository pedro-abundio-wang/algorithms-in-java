package com.cs4everyone.algorithms.stack;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdIn;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class StackTest {

  @Test
  public void linkedStackTest() {
    LinkedStack<String> stack = new LinkedStack<String>();
    In in = new In("tobe.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (!item.equals("-")) {
        stack.push(item);
      } else if (!stack.isEmpty()) {
        StdOut.print(stack.pop() + " ");
      }
    }
    StdOut.println("(" + stack.size() + " left on stack)");
  }

  @Test
  public void arrayStackTest() {
    ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
    In in = new In("tobe.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (!item.equals("-")) {
        stack.push(item);
      } else if (!stack.isEmpty()) {
        StdOut.print(stack.pop() + " ");
      }
    }
    StdOut.println("(" + stack.size() + " left on stack)");
  }
}
