package com.skyscraper.engine.service.javaconfig;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/9/3
 * <p>
 * desc:
 **/

@Component
public class CaffineConfig {

    @Bean
    public Cache provideCache() {
        return Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> "zanzuo");

    }


}
