package com.skyscraper.engine.service.javaconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/

@Configuration
@Slf4j
public class ExecutorServiceProvider {

    @Bean
    public ScheduledExecutorService provideExecutorService() {
        return new ScheduledThreadPoolExecutor(3, r -> {
            Thread thread = new Thread(r, "paper-read-task");

            thread.setUncaughtExceptionHandler(
                    (t1, e) -> {
                        log.info(t1.getName() + "线程抛出的异常" + e);
                    }
            );
            return thread;

        });
//        return Executors.newScheduledThreadPool(1);
    }

}
