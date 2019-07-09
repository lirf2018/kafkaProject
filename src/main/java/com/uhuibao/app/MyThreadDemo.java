package com.uhuibao.app;

/**
 * 创建人: lirf
 * 创建时间:  2018/10/18 15:33
 * 功能介绍:
 */
public class MyThreadDemo {

    public static void main(String[] args) {
        new Thread(new MyThread()).start();
    }
}
