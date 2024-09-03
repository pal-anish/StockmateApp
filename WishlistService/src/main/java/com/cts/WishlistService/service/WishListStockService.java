package com.cts.WishlistService.service;

import com.cts.WishlistService.exception.StockExistAlreadyException;
import com.cts.WishlistService.exception.WishListDoesNotExistException;
import com.cts.WishlistService.model.WishListData;

import java.util.List;

public interface WishListStockService {

    WishListData saveWishListByUsername(WishListData wishListData) throws StockExistAlreadyException;

    List<WishListData> getAllWishListByUsername(String username) throws WishListDoesNotExistException;

    String deleteWishList(String username, int id) throws WishListDoesNotExistException;

}
