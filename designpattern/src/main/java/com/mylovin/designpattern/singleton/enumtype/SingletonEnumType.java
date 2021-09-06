package com.mylovin.designpattern.singleton.enumtype;

public class SingletonEnumType {
    private SingletonEnumType() {
    }

    private static enum SingleEnumInner {
        INSTANCE;

        private final SingletonEnumType instance;

        SingleEnumInner() {
            instance = new SingletonEnumType();
        }

        private SingletonEnumType getInstance() {
            return instance;
        }
    }

    public static SingletonEnumType getInstance() {
        return SingleEnumInner.INSTANCE.getInstance();
    }
}
