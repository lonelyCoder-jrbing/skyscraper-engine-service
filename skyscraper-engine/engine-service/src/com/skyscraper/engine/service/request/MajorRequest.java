package com.skyscraper.engine.service.request;

import lombok.Data;

import java.util.Date;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Data
public class MajorRequest {
    //专业id
    private Long majorId;
    //专业名称
    private String majorName;
    //专业排名
    private String isLiteratureArts;
    //就业率")
    private String hotLevel;
    //创建时间
    private Date createAt = new Date();
    //创建人
    private String createBy = "admin";
    //更新时间
    private Date updateAt = new Date();
    //更新人
    private String updateBy = "admin";


}
