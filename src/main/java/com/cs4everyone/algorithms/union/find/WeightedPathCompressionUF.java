package com.cs4everyone.algorithms.union.find;

public class WeightedPathCompressionUF {

    private int[] parent;

    private int[] size;

    private int count;

    public WeightedPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int root(int p) {
        int rootP = p;
        while (rootP != parent[rootP]) {
            rootP = parent[rootP];
        }

        int nextP = parent[p];
        while (nextP != rootP) {
            parent[p] = rootP;
            p = nextP;
            nextP = parent[nextP];
        }

        return rootP;
    }

    public int count() {
        return count;
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

}
