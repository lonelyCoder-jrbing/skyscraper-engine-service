package com.skyscraper.engine.service.school.dto;

import lombok.Data;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Data
public class QueryCondition {

    //分页页码
    private int page;
    //每页显示数量
    private int size;
    //排序字段
    private String[] properties;

    //专业id
    private Long majorId;
    //专业名称
    private String majorName;
    //专业排名
    private String isLiteratureArts;
    //就业率")
    private String hotLevel;
    //




}
