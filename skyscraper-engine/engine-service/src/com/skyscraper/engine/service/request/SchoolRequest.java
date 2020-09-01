package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Data
public class SchoolRequest extends BaseRequest {

    private Long id;

    private String schoolName;

    private int schoolAge;

    private String schoolAddress;

    private int schoolMajorNum;

    private int schoolRankInChina;

    private int schoolRankInWorld;

    private String is211;

    private String is985;

}
