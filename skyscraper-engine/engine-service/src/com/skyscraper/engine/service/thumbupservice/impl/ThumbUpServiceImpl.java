package com.skyscraper.engine.service.thumbupservice.impl;

import com.skyscraper.engine.jpa.entity.ThumbUp;
import com.skyscraper.engine.jpa.repository.ThumbUpRepository;
import com.skyscraper.engine.service.thumbupservice.ThumbUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Service
public class ThumbUpServiceImpl implements ThumbUpService {

    @Autowired
    ThumbUpRepository thumbUpRepository;

    @Override
    public Boolean genThumbUp(ThumbUp thumbUp) {
        return thumbUpRepository.save(thumbUp) == null ? false : true;
    }

    @Override
    public List<Long> getUserListByPaperId(long paperId) {
        return thumbUpRepository.findByPaperIdIs(paperId)
                .stream()
                .map(el -> el.getUserId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getPaperListByUserId(long userId) {
        return thumbUpRepository.findByUserIdIs(userId);
    }
}
