package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.ThumbUp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
public interface ThumbUpRepository extends JpaRepository<ThumbUp, Long> {

    List<ThumbUp> findByPaperIdIs(long id);

    List<Long> findByUserIdIs(long userId);
}
