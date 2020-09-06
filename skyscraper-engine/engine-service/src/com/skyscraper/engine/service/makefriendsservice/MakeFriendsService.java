package com.skyscraper.engine.service.makefriendsservice;

import com.skyscraper.engine.jpa.entity.User;
import com.skyscraper.engine.service.request.AgreeMakeFriendsWithReq;
import com.skyscraper.engine.service.request.MakeFriendsReq;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
public interface MakeFriendsService {


    boolean makeFriendsWith(MakeFriendsReq req);

    List<User> getOfferFriendsList(long userId);

    List<User> agreeMakeFriendsWith(AgreeMakeFriendsWithReq req);

}

