package com.skyscraper.engine.service.thumbupservice;

import com.skyscraper.engine.jpa.entity.ThumbUp;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
public interface ThumbUpService {


    Boolean genThumbUp(ThumbUp thumbUp);

    List<Long> getUserListByPaperId(long paperId);

    List<Long> getPaperListByUserId(long userId);
}
