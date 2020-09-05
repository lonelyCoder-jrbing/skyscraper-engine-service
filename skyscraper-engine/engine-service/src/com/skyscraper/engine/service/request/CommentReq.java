package com.skyscraper.engine.service.request;

import lombok.Data;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Data
public class CommentReq {

    private long userId;

    private String content;

    private long paperCommentId;

}
