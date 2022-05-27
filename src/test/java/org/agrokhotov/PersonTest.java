package org.agrokhotov;

import org.agrokhotov.spring_introduction.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextAnno.xml");

        Person person = context.getBean("myPerson", Person.class);
        person.callPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());
        context.close();
    }
}
