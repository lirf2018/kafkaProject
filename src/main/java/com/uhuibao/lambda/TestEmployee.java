package com.uhuibao.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/1 10:39
 * 功能介绍:
 */
public class TestEmployee {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("张三", "男", 25));
        employees.add(new Employee("李四", "女", 24));
        employees.add(new Employee("王五", "女", 23));
        employees.add(new Employee("周六", "男", 22));
        employees.add(new Employee("孙七", "女", 21));
        employees.add(new Employee("刘八", "男", 20));

        //俺年龄排序
//        employees.stream().sorted((a, b) -> (a.getAge() - b.getAge())).forEach(System.out::println);
//        List<Employee> sortList = employees.stream().sorted((a, b) -> (a.getAge() - b.getAge())).collect(Collectors.toList());
//        System.out.println(sortList);
        //打印年龄最大的女员工
//        Employee employee = employees.stream().filter(b -> "女".contains(b.getSex())).max((a, b) -> (a.getAge() - b.getAge())).get();
//        System.out.println(employee);
        //打印出年龄大于20 的男员工
//        employees.stream().filter(a -> a.getAge() > 20).filter(a -> "男".equals(a.getSex())).forEach(System.out::println);
//        employees.stream().filter(a -> a.getAge() > 20 && "男".equals(a.getSex())).forEach(System.out::println);
        //limit 方法截取有限的元素
//        employees.stream().sorted((a, b) -> (a.getAge() - b.getAge())).limit(2).forEach(System.out::println);
        //打印出所有男员工的姓名，使用 , 分隔
//       employees.stream().filter(x -> "男".equals(x.getSex())).forEach(System.out::println);
//        String names = employees.stream().filter(x -> "男".equals(x.getSex())).sorted((a, b) -> (a.getAge() - b.getAge())).collect(Collectors.toList()).stream().map(employee -> employee.getName() + employee.getAge()).collect(Collectors.joining(","));
//        System.out.println(names);
        //统计信息
        IntSummaryStatistics stat = employees.stream().mapToInt(e -> e.getAge()).summaryStatistics();
        System.out.println("员工总数：" + stat.getCount());
        System.out.println("最高年龄：" + stat.getMax());
        System.out.println("最小年龄：" + stat.getMin());
        System.out.println("平均年龄：" + stat.getAverage());
        System.out.println("---------------------------------");
//        System.out.println("员工总数：" + employees.size());
//        System.out.println("最高年龄：" + employees.stream().map(e -> e.getAge()).max((a, b) -> (a - b)).get());
//        System.out.println("最小年龄：" + employees.stream().map(e -> e.getAge()).min((a, b) -> (a - b)).get());
//        System.out.println("最高年龄：" + employees.stream().map(e -> e.getAge()).reduce(-1, Integer::max));//给定个初始值
//        System.out.println("最小年龄：" + employees.stream().map(e -> e.getAge()).reduce(1000, Integer::min));//给定个初始值
//        System.out.println("年纪总和：" + employees.stream().map(e -> e.getAge()).reduce(0, Integer::sum));//给定个初始值
//        System.out.println("最高年龄：" + employees.stream().map(e -> e.getAge()).reduce(Integer::max).orElse(0));//无初始值
//        System.out.println("最小年龄：" + employees.stream().map(e -> e.getAge()).reduce(Integer::min).orElse(0));//无初始值
//        System.out.println("年纪总和：" + employees.stream().map(e -> e.getAge()).reduce(Integer::sum).orElse(0));//无初始值
    }
}
