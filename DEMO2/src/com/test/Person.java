package com.test;

public abstract class Person implements Study,Cloneable,test {
    String name;
    int age;
    String sex;
    int id=1900;
    private Status status;
    public Person(String name1,int age1,String sex1){
        this.name=name1;
        this.age=age1;
        this.sex=sex1;
    }

    public void set_status(Status status){
        this.status=status;
    }
    public void get_status(){
        System.out.println(this.status.getName());
    }
    public abstract void say_my_name();

}
