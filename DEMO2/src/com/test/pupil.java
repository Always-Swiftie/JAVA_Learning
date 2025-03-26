package com.test;

public class pupil extends Person implements Study,Imp<String> {
    String name;
    String school;
    public pupil(String name1, int age1, String sex1) {
        super(name1, age1, sex1);
    }

    @Override
    public void say_my_name() {
        System.out.println(super.name+" "+this.school);
    }

    public void say(){
        System.out.println(super.name);
    }
    public void set_school(String school1){
        this.school=school1;
    }

    @Override
    public void study() {
        System.out.println("This is pupil");
    }

    @Override
    public void grade() {
        System.out.println("Grade:A");
    }

    @Override
    public void process(String data) {
        System.out.println(data);
    }

    @Override
    public void say_hello() {
        System.out.println("");
    }
}
