package com.example.graduatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 开启了对Servlet的支持(Tomcat)

@SpringBootApplication
public class GraduateDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduateDemoApplication.class, args);
    }

}
