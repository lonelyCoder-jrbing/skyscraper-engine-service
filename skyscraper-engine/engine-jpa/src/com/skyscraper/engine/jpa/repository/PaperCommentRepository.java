package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.PaperComment;
import com.skyscraper.engine.jpa.entity.SchoolMajorConn;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/

public interface PaperCommentRepository extends JpaRepository<PaperComment, Long> {

}
