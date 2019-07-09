package com.uhuibao.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/2 9:27
 * 功能介绍: 引用构造函数
 */
public class StructureMethodReference {

    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(4, 2, 5, 3, 6, 1);
        //方法引用
        copyElements(list, ArrayList::new);
        //lambda表达示
        copyElements(list, () -> new ArrayList<>());
    }

    private static void copyElements(final List<Integer> list, final Supplier<Collection<Integer>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }
}
