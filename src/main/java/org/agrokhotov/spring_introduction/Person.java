package org.agrokhotov.spring_introduction;

import org.springframework.beans.factory.annotation.Qualifier;

public class Person {
    private Pet pet;
    private String surname;
    private int age;

//    public Person() {}

    public Person(@Qualifier("catBean") Pet pet) {
        System.out.println("Person bean is created");
        this.pet = pet;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPet(Pet pet) {
        System.out.println("Class Person: pet setted");
        this.pet = pet;
    }

    public void callPet() {
        System.out.println("Hi there!");
        pet.say();
    }
}
