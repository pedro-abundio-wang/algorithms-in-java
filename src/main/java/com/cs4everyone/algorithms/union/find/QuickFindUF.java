package com.cs4everyone.algorithms.union.find;

public class QuickFindUF {

    private int[] id;

    private int count;

    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
        }
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        if (pid == qid) return;

        for (int i = 0; i < id.length; i++){
            if (id[i] == pid) {
                id[i] = qid;
            }
        }

        count--;
    }

}
