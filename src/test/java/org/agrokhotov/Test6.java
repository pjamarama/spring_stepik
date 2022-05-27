package org.agrokhotov;

import org.agrokhotov.spring_introduction.Person;
import org.agrokhotov.spring_introduction.Pet;
import org.agrokhotov.spring_introduction.configuration.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test6 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

//        Pet cat = context.getBean("catBean", Pet.class);

        Person person = context.getBean("personBean", Person.class);
        person.callPet();
        context.close();
    }
}
