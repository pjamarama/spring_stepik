package org.agrokhotov;

import org.agrokhotov.spring_introduction.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextAnno.xml");

//        Pet pet = context.getBean("myDog", Pet.class);
//        Person person = new Person(pet);

//        We dont declare and instantiate Dog, Spring does it for us, and injects Dog object to person' constructor
        Person person = context.getBean("myPerson", Person.class);
        person.callPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());
        context.close();
    }
}
