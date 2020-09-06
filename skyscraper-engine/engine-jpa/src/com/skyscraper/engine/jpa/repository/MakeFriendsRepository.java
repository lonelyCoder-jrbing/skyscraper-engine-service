package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
public interface MakeFriendsRepository extends JpaRepository<User, Long> {


    List<User> findByIdIs(long userId);

}
