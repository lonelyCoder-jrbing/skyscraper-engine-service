package com.skyscraper.engine.service.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/

@Configuration
public class ExecutorServiceProvider {

    @Bean
    public ScheduledExecutorService provideExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }


}
