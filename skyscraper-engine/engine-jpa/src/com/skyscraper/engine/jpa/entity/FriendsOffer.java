package com.skyscraper.engine.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@Entity
@Table(name = "t_friends_offer")
@Data
public class FriendsOffer {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_id", nullable = true, length = 20, columnDefinition = "bigint(20) comment '好友列表'")
    private String userId;


    @Column(name = "friend_id", nullable = true, length = 20, columnDefinition = "bigint(20) comment '好友列表'")
    private String friendId;

    @Column(name = "offer_friend_id", nullable = true, length = 20, columnDefinition = "bigint(20) comment '好友列表'")
    private String offerFriendId;

    @Column(name = "offer_content", nullable = true, length = 20, columnDefinition = "varchar(50) comment '申请内容'")
    private String offerContent;

}
