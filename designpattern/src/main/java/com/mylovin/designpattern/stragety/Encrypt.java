package com.mylovin.designpattern.stragety;

public class Encrypt implements Compression {
    @Override
    public void doCompression() {
        System.out.println("加密压缩算法");
    }
}
