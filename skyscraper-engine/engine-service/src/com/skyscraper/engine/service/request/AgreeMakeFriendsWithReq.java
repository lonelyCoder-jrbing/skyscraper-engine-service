package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Data
public class AgreeMakeFriendsWithReq {
    private long userId;
    private long friendsId;

}
