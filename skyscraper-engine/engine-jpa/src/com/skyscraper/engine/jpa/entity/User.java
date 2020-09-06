package com.skyscraper.engine.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Entity
@Table(name = "t_user")
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_name", nullable = true, length = 20, columnDefinition = "varchar(20) comment '用户姓名'")
    private String userName;
    @Column(name = "user_age", nullable = true, length = 20, columnDefinition = "varchar(20) comment '用户年龄'")
    private int userAge;
    @Column(name = "gender", nullable = true, length = 20, columnDefinition = "varchar(10) comment '性别'")
    private String gender;
    @Column(name = "job", nullable = true, length = 20, columnDefinition = "varchar(30) comment '职业'")
    private String job;
    @Column(name = "company", nullable = true, length = 20, columnDefinition = "varchar(30) comment '公司'")
    private String company;
    @Column(name = "friends_id_list", nullable = true, length = 20, columnDefinition = "varchar(100) comment '好友列表'")
    private String friendsIdList;
    @Column(name = "friends_offer_id_list", nullable = true, length = 20, columnDefinition = "varchar(100) comment '添加好友申请列表'")
    private String friendsOfferIdList;
    @Column(name = "offer_content", nullable = true, length = 20, columnDefinition = "varchar(50) comment '申请内容'")
    private String offerContent;

}
