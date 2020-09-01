package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.Major;
import com.skyscraper.engine.jpa.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
public interface MajorRepository extends JpaRepository<Major, Long> {
}
