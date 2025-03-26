package com.test;
//枚举
public enum Status {
    RUNNING("RUN"),STUDY("STU"),SLEEP("SLP");
    private final String name;
    Status(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
