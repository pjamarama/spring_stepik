package org.agrokhotov;

import org.agrokhotov.spring_introduction.Dog;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScopeTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextAnno.xml");

        Dog myDog = context.getBean("dogBean", Dog.class);
        Dog yourDog = context.getBean("dogBean", Dog.class);

        context.close();
    }
}
