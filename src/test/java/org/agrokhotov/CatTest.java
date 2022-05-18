package org.agrokhotov;

import org.agrokhotov.spring_introduction.Cat;
import org.agrokhotov.spring_introduction.Pet;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CatTest {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContextAnno.xml");

        Pet cat = context.getBean("catBean", Cat.class);
        cat.say();

        context.close();
    }

}
