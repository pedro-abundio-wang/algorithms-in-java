package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdIn;
import com.cs4everyone.algorithms.utils.StdOut;

import java.util.Stack;

public class ArithmeticExpressionEvaluation {

  public static void main(String[] args) {
    Stack<String> ops = new Stack<String>();
    Stack<Double> vals = new Stack<Double>();
    In in = new In("evaluation.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (item.equals("(")) {
        ;
      } else if (item.equals("+")) {
        ops.push(item);
      } else if (item.equals("*")) {
        ops.push(item);
      } else if (item.equals(")")) {
        String op = ops.pop();
        if (op.equals("+")) {
          vals.push(vals.pop() + vals.pop());
        } else if (op.equals("*")) {
          vals.push(vals.pop() * vals.pop());
        }
      } else {
        vals.push(Double.parseDouble(item));
      }
    }
    StdOut.println(vals.pop());
  }
}
