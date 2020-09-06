package com.skyscraper.engine.service.papercomment.impl;

import com.skyscraper.engine.jpa.entity.PaperComment;
import com.skyscraper.engine.jpa.repository.MajorRepository;
import com.skyscraper.engine.jpa.repository.PaperCommentRepository;
import com.skyscraper.engine.service.papercomment.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    PaperCommentRepository paperCommentRepository;

    @Override
    public boolean genComment(PaperComment paperComment) {
        return paperCommentRepository.save(paperComment) == null ? false : true;
    }

    @Override
    public List<PaperComment> getAllComment() {
        return paperCommentRepository.findAll();
    }
}
