package com.example.examapp;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ExamappApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamappApplication.class, args);
    }

    //Docket is the object which will have the properties of the configuration for swagger
    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example")).build()
                .apiInfo(apiDetails());
    }
    // private Predicate<String> selectedPaths() {
    //     return or(PathSelectors.ant("/candidates/*"),
    //             PathSelectors.ant("/examiners/*"),
    //             PathSelectors.ant("/exam/*"),
    //             PathSelectors.ant("/question/*"),
    //             PathSelectors.ant("/response/*"));
    // }


    private ApiInfo apiDetails(){
        return new ApiInfo("ACME Exam Taking Application",
                "This application allows authorised candidates to take ACME adaptive exams ",
                "1.0",
                "Terms of Servicce comes here",
                new springfox.documentation.service.Contact("tanujdeep",
                        "www.tanujdeepsingh.ninja",
                        "tanujdeepsingh@gmail.com"),
                "API License",
                "license ka url",
                Collections.emptyList());
    }

}
