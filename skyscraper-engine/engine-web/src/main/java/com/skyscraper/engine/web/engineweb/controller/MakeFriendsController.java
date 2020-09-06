package com.skyscraper.engine.web.engineweb.controller;

import com.skyscraper.engine.jpa.entity.User;
import com.skyscraper.engine.service.common.PaasResponnse;
import com.skyscraper.engine.service.makefriendsservice.MakeFriendsService;
import com.skyscraper.engine.service.request.AgreeMakeFriendsWithReq;
import com.skyscraper.engine.service.request.MakeFriendsReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc: 添加好友申请以及同意
 **/
@RestController
@RequestMapping(value = "/runtime/school")
@Slf4j
public class MakeFriendsController {

    @Autowired
    MakeFriendsService makeFriendsService;

    /*****
     * 申请者发送好友申请
     * @param req
     * @return
     */
    @PostMapping(path = "/makeFriendsWith")
    public PaasResponnse<Boolean> makeFriendsWith(@RequestBody MakeFriendsReq req) {
        return PaasResponnse.success(makeFriendsService.makeFriendsWith(req));
    }

    /*****
     * 获取好友申请列表
     * @param userId
     * @return
     */
    @PostMapping(path = "/getOfferFriendsList")
    public PaasResponnse<List<User>> getOfferFriendsList(@RequestBody long userId) {
        return PaasResponnse.success(makeFriendsService.getOfferFriendsList(userId));
    }


    /*****
     * 同意好友申请,并且展示好友申请列表
     * @param req
     * @return
     */
    @PostMapping(path = "/agreeMakeFriendsWith")
    public PaasResponnse<List<User>> agreeMakeFriendsWith(@RequestBody AgreeMakeFriendsWithReq req) {
        return PaasResponnse.success(makeFriendsService.agreeMakeFriendsWith(req));
    }


}
