package com.uhuibao.lambda;

/**
 * 创建人: lirf
 * 创建时间:  2018/11/1 10:38
 * 功能介绍:
 */
public class Employee {
    private String name;
    private String sex;
    private int age;

    public Employee(String name, String sex, int age) {
        super();
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee {name=").append(name).append(", sex=").append(sex).append(", age=").append(age)
                .append("}");
        return builder.toString();
    }
}
