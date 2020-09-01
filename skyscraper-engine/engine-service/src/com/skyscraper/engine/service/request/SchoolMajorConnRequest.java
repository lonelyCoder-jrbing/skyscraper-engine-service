package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/8/30
 * <p>
 * desc:
 **/
@Data
public class SchoolMajorConnRequest extends BaseRequest{
    private Long id;
    private Long schoolId;
    private Long majorId;
    private Float emplyeeRate;
    private String hotLevel;

}
