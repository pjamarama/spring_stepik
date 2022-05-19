package org.agrokhotov.spring_introduction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("dogBean")
//@Scope("prototype")
public class Dog implements Pet {

    public Dog() {
        System.out.println("Dog bean created");
    }

    @Override
    public void say() {
        System.out.println("Bow-wow!");
    }

    @PostConstruct
    public void init() {
        System.out.println("init method for Dog");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy method for Dog");
    }
}
