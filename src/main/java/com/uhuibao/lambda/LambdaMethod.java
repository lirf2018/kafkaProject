package com.uhuibao.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/1 11:54
 * 功能介绍:
 */
public class LambdaMethod {

    public static void main(String[] args) {
//        new Thread(() -> test01()).start();
        //构造方法引用
//        Car.create(() -> new Car());
//        Car.create(Car::new);
//        Car car = Car.create(Car::new);
//        List<Car> cars = Arrays.asList(car);
        //静态方法引用
//        cars.forEach(Car::collide);
        //类成员方法引用 List中的每个对象执行一次它的repair方法
//        cars.forEach(c -> c.repair());
//        cars.forEach(Car::repair);
//        cars.forEach(c -> c.follow(c));
        //对象方法引用
//        Car police = Car.create(Car::new);
//        Car police2 = Car.create(Car::new);
//        cars.forEach((car1) -> police.follow(car1));
//        cars.forEach(police::follow);
//        cars.forEach(car1 -> car1.follow2(police, police2));
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
