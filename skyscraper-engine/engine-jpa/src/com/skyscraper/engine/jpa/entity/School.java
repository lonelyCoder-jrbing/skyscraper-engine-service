package com.skyscraper.engine.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Entity
@Table(name = "t_school")
@Setter
@Getter
public class School extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "school_name", nullable = true, length = 20)
    private String schoolName;

    @Column(name = "school_age", nullable = true, length = 4)
    private int schoolAge;
    @Column(name = "school_address", nullable = true, length = 10)
    private String schoolAddress;

    @Column(name = "school_major_num", nullable = true, length = 4)
    private int schoolMajorNum;

    @Column(name = "school_rank_in_china", nullable = true, length = 4)
    private int schoolRankInChina;

    @Column(name = "school_rank_in_world", nullable = true, length = 4)
    private int schoolRankInWorld;

    @Column(name = "is_211", nullable = true, length = 4)
    private String is211;

    @Column(name = "is_985", nullable = true, length = 4)
    private String is985;
    @Column(name = "create_at", nullable = true, length = 20)
    private Date createAt;
    @Column(name = "create_by", nullable = true, length = 20)
    private String createBy;
    @Column(name = "update_at", nullable = true, length = 20)
    private Date updateAt;
    @Column(name = "update_by", nullable = true, length = 20)
    private String updateBy;
}
