package com.mylovin.designpattern.stragety;

public class Winrar {
    private Compression compression;

    public Winrar(Compression compression) {
        this.compression = compression;
    }

    public void setCompression(Compression compression) {
        this.compression = compression;
    }

    public void compression() {
        this.compression.doCompression();
    }
}
