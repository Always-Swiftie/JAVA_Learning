package com.test;
//泛型类
public class Score<T> {
    String NAME;
    String ID;
    T VALUE;
    public Score(String name1,String id1,T Value1) {
        this.NAME=name1;
        this.ID=id1;
        this.VALUE=Value1;
    }

    public T getVALUE() {
        return VALUE;
    }
}

