package com.cs4everyone.algorithms.union.find;

public class QuickFindUF {

    private int[] component;

    private int count;

    public QuickFindUF(int n) {
        count = n;
        component = new int[n];
        for (int i = 0; i < n; i++){
            component[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int component(int p) {
        return component[p];
    }

    public boolean isConnected(int p, int q) {
        return component(p) == component(q);
    }

    public void union(int p, int q) {
        int pid = component[p];
        int qid = component[q];

        if (pid == qid) return;

        for (int i = 0; i < component.length; i++){
            if (component[i] == pid) {
                component[i] = qid;
            }
        }

        count--;
    }

}
