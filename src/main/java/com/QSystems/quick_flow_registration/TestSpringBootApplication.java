package com.QSystems.quick_flow_registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestSpringBootApplication
        extends SpringBootServletInitializer
{

//    for jar
//    public static void main(String[] args) {
//        SpringApplication.run(TestSpringBootApplication.class, args);
//    }

//    for war
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestSpringBootApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestSpringBootApplication.class, args);
    }
}
