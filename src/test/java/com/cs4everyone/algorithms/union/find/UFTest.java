package com.cs4everyone.algorithms.union.find;

import com.cs4everyone.algorithms.utils.Datasets;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.net.URL;

public class UFTest {

    @Test
    public void quickFindUFTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(Datasets.LARGE_UF.getName());
        BufferedReader reader = new BufferedReader(new FileReader(url.getFile()));
        String line = reader.readLine();
        QuickFindUF uf = new QuickFindUF(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            int p = Integer.parseInt(line.split(StringUtils.SPACE)[0]);
            int q = Integer.parseInt(line.split(StringUtils.SPACE)[1]);
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }

    @Test
    public void quickUnionUFTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(Datasets.LARGE_UF.getName());
        BufferedReader reader = new BufferedReader(new FileReader(url.getFile()));
        String line = reader.readLine();
        QuickUnionUF uf = new QuickUnionUF(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            int p = Integer.parseInt(line.split(StringUtils.SPACE)[0]);
            int q = Integer.parseInt(line.split(StringUtils.SPACE)[1]);
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }

    @Test
    public void weightedQuickUnionUFTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(Datasets.LARGE_UF.getName());
        BufferedReader reader = new BufferedReader(new FileReader(url.getFile()));
        String line = reader.readLine();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            int p = Integer.parseInt(line.split(StringUtils.SPACE)[0]);
            int q = Integer.parseInt(line.split(StringUtils.SPACE)[1]);
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }


    @Test
    public void weightedPathHalvingUFTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(Datasets.LARGE_UF.getName());
        BufferedReader reader = new BufferedReader(new FileReader(url.getFile()));
        String line = reader.readLine();
        WeightedPathHalvingUF uf = new WeightedPathHalvingUF(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            int p = Integer.parseInt(line.split(StringUtils.SPACE)[0]);
            int q = Integer.parseInt(line.split(StringUtils.SPACE)[1]);
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }

    @Test
    public void weightedPathCompressionUFTest() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(Datasets.LARGE_UF.getName());
        BufferedReader reader = new BufferedReader(new FileReader(url.getFile()));
        String line = reader.readLine();
        WeightedPathCompressionUF uf = new WeightedPathCompressionUF(Integer.parseInt(line));
        while ((line = reader.readLine()) != null) {
            int p = Integer.parseInt(line.split(StringUtils.SPACE)[0]);
            int q = Integer.parseInt(line.split(StringUtils.SPACE)[1]);
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }

}
