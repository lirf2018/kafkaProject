package com.uhuibao.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/2 9:49
 * 功能介绍: 引用特定类型任意对象的实例方法
 */
public class ArbitrayInstenceMethodReference {

    public static void main(String[] args) {
        final List<Person> people = Arrays.asList(new Person("张三"), new Person("王五"));
        //方法引用
//        people.forEach(Person::printName);
        //lambda
//        people.forEach(person -> person.printName());
    }

    private static class Person {
        private String name;

        public Person(final String name) {
            this.name = name;
        }

        public void printName() {
            System.out.println(name);
        }
    }
}
