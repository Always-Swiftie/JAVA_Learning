package com.test;

public interface Study {
    public abstract void study();
    public abstract void grade();//抽象方法
    default public void de_fun(){//在接口中的方法若存在默认实现则不能是抽象方法
        System.out.println("I AM DEFAULT");
    }
}
