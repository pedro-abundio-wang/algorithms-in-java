package com.cs4everyone.algorithm.union.find;

public class WeightedPathHalvingUF {

    private int[] parent;

    private byte[] rank;

    private int count;

    public WeightedPathHalvingUF(int n) {
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public int count() {
        return count;
    }

    @Deprecated
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
            rank[rootQ]++;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

}
