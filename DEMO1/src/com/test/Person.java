package com.test;
public class Person {
    public String name;
    public long age;
    String sex;
    void hello(){
        System.out.println("我叫"+name+",今年"+age+"岁了");
    }
    void setName(String name_to_use){
        this.name=name_to_use;
    }
    public static String info="unfix";
}
