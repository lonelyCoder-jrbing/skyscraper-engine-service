package com.skyscraper.engine.service.scheduletask;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.skyscraper.engine.service.kafka.kafkaProducer;
import com.skyscraper.engine.service.request.PaperRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
@Component
public class ReadPaperTask {
    @Autowired
    ScheduledExecutorService executorService;

    @Autowired
    kafkaProducer kafkaProducer;


    @PostConstruct
    public void init() {
        executorService.scheduleWithFixedDelay(() -> {
            read();
        }, 0L, 10L, TimeUnit.SECONDS);
    }

    public void read() {
        String path = "D:\\project\\skyscraper-engine-service\\skyscraper-engine\\engine-web\\src\\main\\resources\\zanzuo";
        String paperName = path.split("/")[path.split("/").length - 1];
        LoadingCache<Object, String> build = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(key -> paperName);
        File file = new File(path);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
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

            kafkaProducer.send(paperName, JSON.toJSONString(paperRequest));
        } catch (IOException e) {
            e.printStackTrace();
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
