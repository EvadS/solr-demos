package com.se.sample;

import com.se.sample.beans.MyBeans;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringApp {



    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.se.sample");
        context.refresh();

        MyBeans service = context.getBean(MyBeans.class);
        System.out.println(":" + service.getValueFromFile());
    }
}
