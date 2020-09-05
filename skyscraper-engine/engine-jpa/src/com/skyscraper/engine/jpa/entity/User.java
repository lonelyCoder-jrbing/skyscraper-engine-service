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
@Table(name = "t_user")
@Setter
@Getter
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
    @Column(name = "job", nullable = true, length = 20, columnDefinition = "varchar(10) comment '职业'")
    private String job;
    @Column(name = "company", nullable = true, length = 20, columnDefinition = "varchar(10) comment '公司'")
    private String company;

}
