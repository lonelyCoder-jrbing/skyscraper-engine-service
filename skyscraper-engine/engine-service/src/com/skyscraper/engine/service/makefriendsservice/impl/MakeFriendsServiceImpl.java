package com.skyscraper.engine.service.makefriendsservice.impl;

import com.skyscraper.engine.jpa.entity.User;
import com.skyscraper.engine.jpa.repository.MakeFriendsRepository;
import com.skyscraper.engine.service.makefriendsservice.MakeFriendsService;
import com.skyscraper.engine.service.request.AgreeMakeFriendsWithReq;
import com.skyscraper.engine.service.request.MakeFriendsReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class MakeFriendsServiceImpl implements MakeFriendsService {


    @Autowired
    MakeFriendsRepository makeFriendsRepository;

    @Override
    public boolean makeFriendsWith(MakeFriendsReq req) {
        User one = makeFriendsRepository.getOne(req.getFriendId());
        String friendsIdList = one.getFriendsIdList();
        log.info("makeFriendsWith@@friendsIdList======{}", friendsIdList);
        if (StringUtils.isNotEmpty(friendsIdList)) {
            String newFriendsIdList = Arrays.stream(friendsIdList.concat(",")
                    .concat(String.valueOf(req.getId())).split(","))
                    .collect(Collectors.toSet())
                    .stream().map(el -> el.concat(","))
                    .collect(Collectors.joining());

            one.setFriendsOfferIdList(newFriendsIdList);
            one.setOfferContent(req.getOfferContent());
        } else {
            one.setOfferContent(req.getOfferContent());
            one.setFriendsOfferIdList(String.valueOf(req.getId()));
            log.info("makeFriendsWith@@one======{}", one);
        }
        return makeFriendsRepository.save(one) == null ? false : true;
    }

    @Override
    public List<User> getOfferFriendsList(long userId) {
        User user = makeFriendsRepository.findById(userId).get();
        String friendsIdList = user.getFriendsOfferIdList();
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
            Set<String> set = Arrays.stream(user.getFriendsIdList().concat(",")
                    .concat(String.valueOf(req.getFriendsId())).split(","))
                    .collect(Collectors.toSet());
            String joining = "";
            if (set.size() > 1) {
                joining = set.stream().collect(Collectors.joining(","));
                user.setFriendsIdList(joining);
            } else {
                ArrayList<String> strings = new ArrayList<>();
                strings.addAll(set);
                user.setFriendsIdList(strings.get(0));
            }
        } else {
            user.setFriendsIdList(String.valueOf(req.getFriendsId()));
        }
        makeFriendsRepository.saveAndFlush(user);
        String friendsIdList = makeFriendsRepository.findById(Long.valueOf(req.getUserId())).get().getFriendsIdList();
        if (StringUtils.isNotEmpty(friendsIdList)) {
            List<Long> UserIds = Arrays.stream(friendsIdList.split(",")).map(el -> Long.valueOf(el)).collect(Collectors.toList());
            return makeFriendsRepository.findAllById(UserIds);
        }
        return null;
    }
}
