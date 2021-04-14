package com.cs4everyone.algorithms.search;

import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdOut;
import org.junit.Test;

public class SequentialSearchSTTest {

    @Test
    public void test() {
        SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
        In in = new In("tinyST.txt");
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

    }

}
