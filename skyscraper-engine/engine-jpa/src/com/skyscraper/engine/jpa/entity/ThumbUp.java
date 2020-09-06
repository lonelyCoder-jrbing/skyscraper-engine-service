package com.skyscraper.engine.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Entity
@Table(name = "t_thumbup")
@Setter
@Getter
public class ThumbUp {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_id", nullable = true, length = 20, columnDefinition = "bigint(20) comment '用户id'")
    private long userId;
    @Column(name = "paper_id", nullable = true, length = 20, columnDefinition = "bigint(20) comment '文章id'")
    private long paperId;

}
