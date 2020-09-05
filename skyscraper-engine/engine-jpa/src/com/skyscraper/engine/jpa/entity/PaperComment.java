package com.skyscraper.engine.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * create by sumerian on 2020/9/5
 * <p>
 * desc:
 **/
@Entity
@Table(name = "t_paper_comment")
@Setter
@Getter
public class PaperComment {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_id", nullable = true, length = 200,columnDefinition="bigint(20) comment '用户id'")
    private long userId;

    @Column(name = "comment_content", nullable = true, length = 200,columnDefinition="text comment '文章评论'")
    private String commentContent;

    @Column(name = "paper_comment_id", nullable = true, length = 20,columnDefinition="bigint(20) comment '文章关联id'")
    private Long paperCommentId;



}
