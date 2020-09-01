package com.skyscraper.engine.service.request;

import lombok.Data;

import java.util.Date;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Data
public class BaseRequest {
    private Date createAt = new Date();
    private String createBy = "admin";
    private Date updateAt = new Date();
    private String updateBy = "admin";
}
