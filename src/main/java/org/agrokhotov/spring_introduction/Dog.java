package org.agrokhotov.spring_introduction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Dog implements Pet {

    public Dog() {
        System.out.println("Dog bean created");
    }

    @Override
    public void say() {
        System.out.println("Bow-wow!");
    }

}
