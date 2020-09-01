package com.skyscraper.engine.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/

public class BaseEntity {
    @Column(name = "create_at", nullable = true, length = 20)
    private Date createAt;
    @Column(name = "create_by", nullable = true, length = 20)
    private String createBy;
    @Column(name = "update_at", nullable = true, length = 20)
    private Date updateAt;
    @Column(name = "update_by", nullable = true, length = 20)
    private String updateBy;
}
