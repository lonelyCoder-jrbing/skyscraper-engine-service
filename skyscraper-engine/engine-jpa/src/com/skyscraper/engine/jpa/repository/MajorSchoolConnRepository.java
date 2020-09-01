package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.Major;
import com.skyscraper.engine.jpa.entity.SchoolMajorConn;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by sumerian on 2020/8/30
 * <p>
 * desc:
 **/
public interface MajorSchoolConnRepository extends JpaRepository<SchoolMajorConn, Long> {
}
