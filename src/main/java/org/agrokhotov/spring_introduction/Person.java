package org.agrokhotov.spring_introduction;

public class Person {
    private Pet pet;
    private String surname;
    private int age;

    public Person() {
        System.out.println("Person created");
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
        System.out.println("Pet setted");
        this.pet = pet;
    }

    public void callPet() {
        System.out.println("Hi there!");
        pet.say();
    }
}
