package com.uhuibao.app;

import com.uhuibao.util.DateUtil;

/**
 * 创建人: lirf
 * 创建时间:  2018/10/18 15:32
 * 功能介绍:
 */
public class MyThread implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                System.out.println(DateUtil.getNow());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
