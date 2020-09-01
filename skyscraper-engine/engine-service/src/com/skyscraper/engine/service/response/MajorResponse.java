package com.skyscraper.engine.service.response;

import lombok.Builder;
import lombok.Data;

/**
 * create by sumerian on 2020/8/30
 * <p>
 * desc:
 **/
@Data
@Builder
public class MajorResponse {

    //专业名称
    private String majorName;
    //专业排名
    private String isLiteratureArts;
    //专业热门等级
    private String hotLevel;
    //就业率")
    private Float emplyeeRate;
    //在本校的受追捧程度
    private String hotLevelInSchool;


}
