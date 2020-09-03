package com.skyscraper.engine.service.request;

import lombok.Data;

import java.io.Serializable;

/**
 * create by sumerian on 2020/9/2
 * <p>
 * desc:
 **/
@Data
public class PaperRequest implements Serializable {
    private static final long serialVersionUID = 8347634164394128232L;
    private String paperId;
    private String content;
    private String title;
    private String major;
    private String school;

}
