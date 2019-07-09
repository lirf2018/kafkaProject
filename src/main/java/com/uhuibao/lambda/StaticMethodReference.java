package com.uhuibao.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/2 9:24
 * 功能介绍: 引用静态方法
 */
public class StaticMethodReference {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 2, 5, 3, 6, 1);
        //静态方法引用
//        list.forEach(LambdaMethod::print);
        //lambda表达示
        list.forEach(number -> LambdaMethod.print(number));

    }

    //引用静态方法
    public static void print(final int number) {
        System.out.println("---->number:" + number);
    }
}
