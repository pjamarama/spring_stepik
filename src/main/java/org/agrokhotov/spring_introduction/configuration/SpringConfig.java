package org.agrokhotov.spring_introduction.configuration;

import org.agrokhotov.spring_introduction.Cat;
import org.agrokhotov.spring_introduction.Person;
import org.agrokhotov.spring_introduction.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:myApp.properties")
public class SpringConfig {

    @Bean
    public Pet catBean() {
        return new Cat();
    }

    @Bean
    public Person personBean() {
        return new Person(catBean());
    }
}
