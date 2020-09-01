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
@Table(name = "t_major")
@Data
public class Major {
    @Id
    @GeneratedValue
    private Long majorId;

    @Column(name = "major_name", nullable = true, length = 20)
    private String majorName;

    @Column(name = "is_literature_arts", nullable = true, length = 20)
    private String isLiteratureArts;

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
