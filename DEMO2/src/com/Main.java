package com;

import com.test.Person;
import com.test.S_mat;
import com.test.Study;
import com.test.pupil;
import com.test.Status;
import com.test.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(java.lang.String[] args) {
        pupil p1=new pupil("lbwnb",19,"female");
        p1.process("LBWNBA");//两种实现泛型接口类中方法的模式
        Imp<Integer> IP1=data -> System.out.println(data.equals(19));//Lambda表达式
        IP1.process(19);
        test tes1=new test() {
            @Override
            public void say_hello() {
                System.out.println("我是匿名类！");
            }
        };
        tes1.say_hello();
    }

    public  static <T> T func(T t){
        return t;
    }

}


