package com.skyscraper.engine.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * create by sumerian on 2020/8/30
 * <p>
 * desc:
 **/
//alter table `t_school_major_conn` convert to character set utf8;
@Entity
@Table(name = "t_school_major_conn")
@Setter
@Getter
public class SchoolMajorConn {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "school_id", nullable = true, length = 20)
    private Long schoolId;
    @Column(name = "major_id", nullable = true, length = 20)
    private Long majorId;
    @Column(name = "emplyee_rate", nullable = true, length = 20)
    private Float emplyeeRate;
    @Column(name = "hot_level", nullable = true, length = 20)
    private String hotLevel;

    @Column(name = "create_at", nullable = true, length = 20)
    private Date createAt;
    @Column(name = "create_by", nullable = true, length = 20)
    private String createBy;
    @Column(name = "update_at", nullable = true, length = 20)
    private Date updateAt;
    @Column(name = "update_by", nullable = true, length = 20)
    private String updateBy;

}
