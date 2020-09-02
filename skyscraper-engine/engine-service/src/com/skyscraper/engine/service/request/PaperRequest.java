package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
@Data
public class PaperRequest {
    private String paperId;
    private String content;
    private String title;
    private String major;
    private String school;

}
