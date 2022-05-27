package org.agrokhotov.spring_introduction.configuration;

import org.agrokhotov.spring_introduction.Cat;
import org.agrokhotov.spring_introduction.Person;
import org.agrokhotov.spring_introduction.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class SpringConfig {

    @Bean // beanId здесь - название метода, catBean
    public Pet catBean() {
        return new Cat();
    }

    @Bean
    public Person personBean() {
        return new Person(catBean());
    }
}
