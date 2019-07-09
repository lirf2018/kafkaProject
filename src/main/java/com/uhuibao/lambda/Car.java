package com.uhuibao.lambda;

import java.util.function.Supplier;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/1 15:11
 * 功能介绍:
 */
public class Car {
    // 通过Supplier获取Car实例

    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }

    // 静态方法，一个入参Car对象
    public static void collide(final Car car) {
        System.out.println("Collide " + car.toString());
    }

    // 一个入参Car
    public void follow(final Car car) {
        System.out.println("Following car " + car.toString());
    }

    // 一个入参Car
    public void follow2(final Car car, final Car car2) {
        System.out.println("Followingssssss222 car " + car.toString());
    }

    // 不带入参
    public void repair() {
        System.out.println("Repaired car " + this.toString());
    }
}
