package org.agrokhotov.spring_introduction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Cat implements Pet {
    public Cat() {
        System.out.println("Cat bean created");
    }

    @Override
    public void say() {
        System.out.println("Meow-meow!");
    }

//    @PostConstruct
//    public void init() {
//        System.out.println("init method");
//    }
//
//    @PreDestroy
//    public void destroy() {
//        System.out.println("destroy method");
//    }
}
