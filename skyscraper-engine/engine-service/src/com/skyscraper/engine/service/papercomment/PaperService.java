package com.skyscraper.engine.service.papercomment;

import com.skyscraper.engine.jpa.entity.PaperComment;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
public interface PaperService {
    boolean genComment(PaperComment paperComment);

    List<PaperComment> getAllComment();

}
