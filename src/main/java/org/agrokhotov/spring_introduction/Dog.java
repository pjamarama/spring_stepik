package org.agrokhotov.spring_introduction;

public class Dog implements Pet {

    public Dog() {
        System.out.println("Dog bean created");
    }

    @Override
    public void say() {
        System.out.println("Bow-wow!");
    }

    public void init() {
        System.out.println("init method for Dog");
    }

    public void destroy() {
        System.out.println("destroy method for Dog");
    }
}
