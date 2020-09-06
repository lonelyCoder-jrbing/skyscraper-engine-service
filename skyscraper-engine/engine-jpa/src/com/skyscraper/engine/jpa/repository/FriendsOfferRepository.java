package com.skyscraper.engine.jpa.repository;

import com.skyscraper.engine.jpa.entity.FriendsOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
public interface FriendsOfferRepository extends JpaRepository<FriendsOffer, Long> {
    @Query(value = "select offer_friend_id from t_friends_offer where user_id = ?", nativeQuery = true)
    List<Long> queryFriendsOfferByIdUseSQL(long userId);
}
