package org.agrokhotov;

import org.agrokhotov.spring_introduction.Person;
import org.agrokhotov.spring_introduction.configuration.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        Person person = context.getBean("myPerson", Person.class);
        person.callPet();
        System.out.println(person.getSurname());
        System.out.println(person.getAge());
        context.close();
    }
}
