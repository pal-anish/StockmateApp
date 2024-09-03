package com.cts.WishlistService.repository;

import com.cts.WishlistService.model.WishListData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishListRepository extends JpaRepository<WishListData,Integer> {

    @Query("select u from WishListData u where u.username = :username")
    List<WishListData> findWishlistByUsername(String username);

    @Query("select u from WishListData u where u.username = :username and u.symbol = :symbol")
    Optional<WishListData> findByUserNameAndSymbol(String  username, String symbol);

    @Query("select u from WishListData u where u.username = :username and u.wishlistId = :id")
    Optional<WishListData> findByUserNameAndId(String username, int id);

}
