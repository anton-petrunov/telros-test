package io.github.anton_petrunov.telros_test;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TelrosTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelrosTestApplication.class, args);
    }

//    https://stackoverflow.com/a/37840526
    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

}
