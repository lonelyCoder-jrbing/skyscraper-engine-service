package com.skyscraper.engine.web.engineweb.controller;

import com.skyscraper.engine.jpa.entity.PaperComment;
import com.skyscraper.engine.jpa.entity.School;
import com.skyscraper.engine.service.common.PaasResponnse;
import com.skyscraper.engine.service.papercomment.PaperService;
import com.skyscraper.engine.service.request.CommentReq;
import com.skyscraper.engine.service.request.SchoolRequest;
import com.skyscraper.engine.web.engineweb.utils.IsNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/9/5
 * <p>
 * desc:文章评论模块的设计
 **/
@RestController
@RequestMapping(value = "/runtime/school")
@Slf4j
public class PaperController {

    @Autowired
    PaperService paperService;


    @PostMapping(path = "/genComment")
    public PaasResponnse<Boolean> genComment(@RequestBody CommentReq commentReq) {
        List<String> strings = IsNull.validateProperty(
                commentReq,
                "paperCommentId");
        System.out.println("commentReq:" + commentReq);
        if (strings.size() > 0) {
            return PaasResponnse.fail(false, "" + strings);
        }
        PaperComment paperComment = new PaperComment();

        BeanUtils.copyProperties(commentReq, paperComment);
        log.info("paperComment===========:{}", paperComment);
        boolean b = paperService.genComment(paperComment);
        return PaasResponnse.success(b);
    }

    @PostMapping(path = "/getAllComment")
    public PaasResponnse<Map<Long, PaperComment> > getAllComment() {

        PaperComment paperComment = new PaperComment();
        List<PaperComment>list = paperService.getAllComment();
        Map<Long, PaperComment> map = list.stream().collect(Collectors.toMap(PaperComment::getId, Function.identity(), (k1, k2) -> k1));
        return PaasResponnse.success(map);
    }




}
