import com.test.Person;
package com.test.Person;
public class Main {
    public static void main(String[] args) {
        Person p1= new Person();
        System.out.println(p1.name+p1.age);
        System.out.println(Person.info);
        p1.info="hello";
    }

}