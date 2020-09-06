package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Data
public class MakeFriendsReq {

    //自己的id
    private long id;
    //朋友的id
    private long friendId;
    //申请的内容
    private String offerContent;
}
