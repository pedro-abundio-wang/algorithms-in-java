package com.cs4everyone.algorithms.utils;

public enum Datasets {

    TINY_UF("tinyUF.txt"),
    MEDIUM_UF("mediumUF.txt"),
    LARGE_UF("largeUF.txt");

    private String name;

    Datasets(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
