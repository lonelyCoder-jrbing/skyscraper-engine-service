package com.skyscraper.engine.service.makefriendsservice.impl;

import com.skyscraper.engine.jpa.entity.User;
import com.skyscraper.engine.jpa.repository.MakeFriendsRepository;
import com.skyscraper.engine.service.makefriendsservice.MakeFriendsService;
import com.skyscraper.engine.service.request.AgreeMakeFriendsWithReq;
import com.skyscraper.engine.service.request.MakeFriendsReq;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Service
public class MakeFriendsServiceImpl implements MakeFriendsService {


    @Autowired
    MakeFriendsRepository makeFriendsRepository;

    @Override
    public boolean makeFriendsWith(MakeFriendsReq req) {
        User one = makeFriendsRepository.getOne(req.getFriendId());
        String friendsIdList = one.getFriendsIdList();
        if (friendsIdList != null) {
            friendsIdList.concat(",").concat(String.valueOf(req.getId()));
        }
        one.setOfferContent(req.getOfferContent());
        return makeFriendsRepository.saveAndFlush(one) == null ? false : true;
    }

    @Override
    public List<User> getOfferFriendsList(long userId) {
        User user = makeFriendsRepository.findById(userId).get();
        String friendsIdList = user.getFriendsIdList();
        if (StringUtils.isNotEmpty(friendsIdList)) {
            String[] split = friendsIdList.split(",");
            List<Long> list = Arrays.stream(split).map(el -> Long.valueOf(el)).collect(Collectors.toList());
            List<User> userList = makeFriendsRepository.findAllById(list);
            return userList;
        } else {
            return null;
        }

    }

    @Override
    public List<User> agreeMakeFriendsWith(AgreeMakeFriendsWithReq req) {
        User user = makeFriendsRepository.findById(Long.valueOf(req.getUserId())).get();
        if (StringUtils.isNotEmpty(user.getFriendsIdList())) {
            String friendsIdList = user.getFriendsIdList().concat(String.valueOf(req.getFriendsId()));
            user.setFriendsIdList(friendsIdList);
        } else {
            user.setFriendsIdList(String.valueOf(req.getFriendsId()));
        }
        makeFriendsRepository.saveAndFlush(user);
        String friendsIdList = makeFriendsRepository.findById(Long.valueOf(req.getUserId())).get().getFriendsIdList();
        if(StringUtils.isNotEmpty(friendsIdList)){
            List<Long> UserIds = Arrays.stream(friendsIdList.split(",")).map(el -> Long.valueOf(el)).collect(Collectors.toList());
            return makeFriendsRepository.findAllById(UserIds);
        }
        return null;
    }
}
