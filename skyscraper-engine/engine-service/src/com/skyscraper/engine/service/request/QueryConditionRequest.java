package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Data
public class QueryConditionRequest {

    //分页页码
    private int page;
    //每页显示数量
    private int size;
    //排序字段
    private String[] properties;

}
