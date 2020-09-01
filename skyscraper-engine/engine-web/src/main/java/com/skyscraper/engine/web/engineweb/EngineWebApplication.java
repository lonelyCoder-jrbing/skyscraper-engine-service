package com.skyscraper.engine.web.engineweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.skyscraper.*")
@EnableJpaRepositories("com.skyscraper.engine.jpa")
@EntityScan("com.skyscraper.engine.jpa.entity")
public class EngineWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineWebApplication.class, args);
    }

}
