package org.agrokhotov.spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myPerson")
public class Person {
    private Pet pet;

    @Value("${person.surname}")
    private String surname;

    @Value("${person.age}")
    private int age;

////    public Person() {
////        System.out.println("Person created");
////    }
//
//    @Autowired
//    public Person(Pet pet) {
//        System.out.println("Person bean created");
//        this.pet = pet;
//    }

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

    @Autowired
    @Qualifier("catBean")
    public void setPet(Pet pet) {
        System.out.println("Class Person: pet setted");
        this.pet = pet;
    }

    public void callPet() {
        System.out.println("Hi there!");
        pet.say();
    }
}
