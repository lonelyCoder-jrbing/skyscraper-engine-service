package com.skyscraper.engine.service.scheduletask;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.Cache;
import com.skyscraper.engine.service.kafka.kafkaProducer;
import com.skyscraper.engine.service.request.PaperRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;
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

    @Value("${paperpath}")
    private String path;

    @PostConstruct
    public void init() {
        log.info("start executorService....");
        ScheduledFuture<?> read_error = executorService.scheduleWithFixedDelay(() -> {
            try {
                log.info(Thread.currentThread().getName() + " " + "i am working...");
                read();
            } catch (Exception error) {
                log.error("error={},line={}", error, error.getMessage());
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
        log.info("path: {}", path);
//        String path = "D:\\books";
        try {
            Map<String, Object> txtmap = getTxt(path);
            txtmap.forEach((name, content) -> {
                String s = JSON.toJSONString(PaperRequest
                        .builder()
                        .title(name)
                        .content((String) content)
                        .major("demo")
                        .paperId(UUID.randomUUID().toString())
                        .school("demoSchool")
                        .build());
                if (Objects.nonNull(cache.getIfPresent(name))) {
                    kafkaProducer.send(name, s);
                }
                cache.get(name, n -> name);
            });
        } catch (IOException e) {
            log.info("e=={}", e);
        } finally {
        }
    }


    // 读取txt内容
    public static String txt2String(File file) {
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            // 构造一个BufferedReader类来读取文件
            String s = null;
            // 使用readLine方法，一次读一行
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator() + s);
            }
        } catch (Exception e) {
            log.error("error={}", e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                log.error("error={}", e);
            }
        }
        return result.toString();
    }

    // 读取文件夹下所有文件名
    static List listLocal = new ArrayList<>();

    public static List getFile(File file) {
        if (file != null) {
            File[] f = file.listFiles();

            if (f != null) {

                for (int i = 0; i < f.length; i++) {
                    getFile(f[i]);
                    listLocal.add(f[i]);
                }
            }
        }
        return listLocal;
    }

    public Map<String, Object> getTxt(String path) throws IOException {
        // 文件夹下每一个txt名
//        String path = "D:\books";
        File all = new File(path);
        //将全部txt存到list中
        List<File> allPath = getFile(all);
        Map<String, Object> map = new HashMap<>();
        // 读取txt内容 并转换成List
        allPath.stream().filter(el -> el.getName().contains(".pdf")).forEach(el -> {
            if (map.size() < 10) {
                map.put(el.getName(), txt2String(el));
            }
        });
        return map;
    }

}
