package com.feri.selfcoding_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ImportResource("classpath:dubbocustomer.xml")
@EnableSwagger2
public class SelfcodingWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfcodingWebappApplication.class, args);
    }

}

