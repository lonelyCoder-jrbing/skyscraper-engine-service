package com.skyscraper.engine.service.scheduletask;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.skyscraper.engine.service.kafka.kafkaProducer;
import com.skyscraper.engine.service.request.PaperRequest;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.NonNegative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
@Component
@Slf4j
public class ReadPaperTask {
    @Autowired
    ScheduledExecutorService executorService;

    @Autowired
    kafkaProducer kafkaProducer;

    @Autowired
    Cache cache;

    @PostConstruct
    public void init() {
        log.info("start executorService....");
        ScheduledFuture<?> read_error = executorService.scheduleWithFixedDelay(() -> {
            try {
                log.info(Thread.currentThread().getName() + " " + "i am working...");
                read();
            } catch (Exception error) {
                log.error("error={},line={}",error,error.getMessage());
            }
        }, 0L, 10L, TimeUnit.SECONDS);


    }

    @PreDestroy
    public void destory() {
        log.info("destory executorService...");
        executorService.shutdown();
    }

    public void read() {
        log.info("read paper.....");

        String path = "D:\\project\\skyscraper-engine-service\\skyscraper-engine\\engine-web\\src\\main\\resources\\zanzuo";
        String paperName = path.split("/")[path.split("").length - 1];
//        String paperName = "zanzuo";
        log.info("papername           :{}", paperName);

        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            File file = new File(path);
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            PaperRequest paperRequest = new PaperRequest();
            paperRequest.setContent(sbf.toString());
            paperRequest.setMajor("demo");
            paperRequest.setPaperId(UUID.randomUUID().toString());
            paperRequest.setSchool("demo");
            paperRequest.setTitle(paperName);
            @NonNegative long l = cache.estimatedSize();
            log.info("l:  {}", l);
            if (l < 1) {
                kafkaProducer.send(paperName, JSON.toJSONString(paperRequest));
            }
            cache.get(paperName, v -> sbf.toString());
        } catch (IOException e) {
            log.info("e=={}", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

    }


}
