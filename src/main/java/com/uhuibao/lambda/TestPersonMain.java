package com.uhuibao.lambda;


import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 创建人: lirf
 * 创建时间:  2018/10/19 15:11
 * 功能介绍:
 */
public class TestPersonMain {

    public static void main(String[] args) throws Exception {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, new BigDecimal("2100")));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, new BigDecimal("1500")));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, new BigDecimal("1800")));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, new BigDecimal("1600")));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, new BigDecimal("1200")));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, new BigDecimal("1900")));
                add(new Person("Shawn", "Randall", "Java programmer", "female", 30, new BigDecimal("2300")));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, new BigDecimal("1700")));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, new BigDecimal("2000")));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, new BigDecimal("1300")));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, new BigDecimal("1550")));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, new BigDecimal("1200")));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, new BigDecimal("1600")));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, new BigDecimal("1000")));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, new BigDecimal("1100")));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, new BigDecimal("1300")));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, new BigDecimal("1100")));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, new BigDecimal("1000")));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, new BigDecimal("1600")));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, new BigDecimal("1800")));
            }
        };


        //输出
//        javaProgrammers.forEach((p) -> System.out.println(p.getFirstName() + "--" + p.getLastName() + "---" + p.getSalary()));
//        phpProgrammers.forEach(p -> System.out.println(p.getFirstName() + "--" + p.getLastName() + "---" + p.getSalary()));

//        javaProgrammers.stream().filter(person -> (person.getSalary().compareTo(new BigDecimal("2000"))>-1)).forEach(person -> System.out.println(JSONObject.toJSONString(person)));
//        javaProgrammers.forEach(person -> person.setSalary(person.getSalary().multiply(new BigDecimal("0.1"))));

        //自定义filter  Predicate(Predicate是一个函数式接口，可以被应用于lambda表达式和方法引用。其抽象方法非常简单)
//        Predicate<Person> ageFilter = (p) -> (p.getAge() > 30);
//        Predicate<Person> salaryFilter = (p) -> (p.getSalary().compareTo(new BigDecimal("2000")) > -1);
//        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));
//        javaProgrammers.stream().filter(salaryFilter).filter(ageFilter).forEach(person -> System.out.println(JSONObject.toJSONString(person)));
        //排序
//        javaProgrammers.stream().sorted((s1, s2) -> (s1.getAge() - s2.getAge())).forEach(person -> System.out.println(JSONObject.toJSONString(person)));
//        javaProgrammers.stream().sorted((s1, s2) -> (s1.getFirstName().compareTo(s2.getFirstName()))).forEach(person -> System.out.println(JSONObject.toJSONString(person)));
//        javaProgrammers.stream().sorted((s1, s2) -> (s1.getSalary().compareTo(s2.getSalary()))).forEach(person -> System.out.println(JSONObject.toJSONString(person)));
//        Person pMax= javaProgrammers.stream().max((s1, s2) -> (s1.getSalary().compareTo(s2.getSalary()))).get();
//        System.out.println(pMax.getSalary());
//        Person pMin = javaProgrammers.stream().min((s1, s2) -> (s1.getSalary().compareTo(s2.getSalary()))).get();
//        System.out.println(pMin.getSalary());
//        List<String> personList = javaProgrammers.stream().map(person -> person.getFirstName().toUpperCase()).collect(Collectors.toList());
//        System.out.println(personList);
//        List costList = Arrays.asList(100, 200, 300, 400, 500);
//        costBeforeTax.stream().map((cost) -> Integer.parseInt(cost.toString()) + 2*Integer.parseInt(cost.toString())) .forEach(System.out::println);
//        List<Integer> primes = Arrays.asList(2, 19, 5, 3, 11, 13, 17, 7, 23, 29);
//        Collections.sort(primes);
//        System.out.println(primes);
//        long count = primes.stream().mapToInt(x -> x).count();
//        System.out.println("count=" + count);
//        int sum = primes.stream().mapToInt(x -> x).sum();
//        System.out.println("sum=" + sum);
//        计算List中的元素的最大值，最小值，总和及平均值
//        IntSummaryStatistics intSummaryStatistics = primes.stream().mapToInt(x -> x).summaryStatistics();
//        System.out.println(intSummaryStatistics.getAverage());
//        System.out.println(intSummaryStatistics.getCount());
//        System.out.println(intSummaryStatistics.getMax());
//        System.out.println(intSummaryStatistics.getMin());
//        System.out.println(intSummaryStatistics.getSum());
//        primes.stream().filter(x -> x > 10).forEach(o -> System.out.println(o));
//        primes.stream().sorted((s1, s2) -> (s1 - s2)).filter(x -> x > 10).forEach(x -> System.out.println(x));
//        new Thread(() -> System.out.println("java8")).start();
//        primes.stream().filter(x -> x > 15).forEach(System.out::println);
//        List<Integer> integerList = primes.stream().filter(x -> x > 20).collect(Collectors.toList());
//        integerList.stream().forEach(System.out::println);
//        List<Integer> primes = Arrays.asList(2, 5, 3);
//        int ax = primes.stream().map(x -> x * 1).reduce((a, x) -> a + x).get();
//        System.out.println(ax);
//        int ax = primes.stream().reduce((a, b) -> a + b).get();
//        System.out.println(ax);
        //上面是将字符串转换为大写，然后使用逗号串起来。
//        List<String> g7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
//        g7.stream().map(x -> x.toUpperCase()).forEach(System.out::println);
//        List<String> g7_1 = g7.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
//        g7_1.stream().forEach(System.out::println);
        Predicate<String> filter1 = x -> x.startsWith("U");
//        Predicate<String> filter2 = x -> x.startsWith("J");
//        g7.stream().filter(filter1).forEach(System.out::println);
//        g7.stream().filter(filter1.or(filter2)).sorted((a, b) -> (a.compareTo(b))).forEach(System.out::println);
        //Java 8中使用lambda表达式的Map和Reduce示例
//        List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
//        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        //对列表的每个元素应用函数 G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
//        String str = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining("--"));
//        System.out.println(str);
        //排序
//        List<Integer> primes = Arrays.asList(2, 19, 5, 3, 11, 13, 17, 7, 23, 29);
//        primes.stream().sorted((x, y) -> (x - y)).forEach(System.out::println);
//        List<Integer> sortList = primes.stream().sorted((x, y) -> (x - y)).collect(Collectors.toList());
        //筛选出只有2个字的水果
//        List<String> fruit = Arrays.asList("香蕉", "哈密瓜", "榴莲", "火龙果", "水蜜桃");
//        fruit.stream().filter(x -> x.length() == 2).forEach(System.out::println);
        Integer[] a = {1, 8, 3, 9, 2, 0, 5};
//        Arrays.sort(a, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        });
//        Arrays.sort(a, (x, y) -> (x - y));
//        System.out.println(JSONObject.toJSONString(a));
        //实例上实例方法引用
        List<String> names = Arrays.asList(new String[]{"张三", "李四", "王h五"});
//        Predicate<String> checkNameExists = names::contains;
//        System.out.println(checkNameExists.test("张三"));
//        System.out.println(checkNameExists.test("张四"));
//        下面的函数来返回字符串的长度
        Function<String, Integer> calcStrLength = String::length;
//        System.out.println(calcStrLength.apply("张三"));
        names.stream().map(String::length).filter(x -> x > 2).forEach(System.out::println);
    }
}
