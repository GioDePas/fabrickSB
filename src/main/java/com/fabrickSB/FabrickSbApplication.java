package com.fabrickSB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class FabrickSbApplication {
    public static void main(String[] args) {
        ApplicationContext cx = SpringApplication.run(FabrickSbApplication.class, args);
        Arrays.stream(cx.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
