package com.cts.WishlistService.serviceImpl;

import com.cts.WishlistService.exception.StockExistAlreadyException;
import com.cts.WishlistService.exception.WishListDoesNotExistException;
import com.cts.WishlistService.model.WishListData;
import com.cts.WishlistService.repository.WishListRepository;
import com.cts.WishlistService.service.WishListStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListStockServiceImpl implements WishListStockService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    public WishListStockServiceImpl(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    @Override
    public WishListData saveWishListByUsername(WishListData wishListData) throws StockExistAlreadyException {
        Optional<WishListData> wishListData1 = wishListRepository.findByUserNameAndSymbol(wishListData.getUsername(), wishListData.getSymbol());
        if (wishListData1.isPresent()) {
            throw new StockExistAlreadyException("Stock Already Exist");
        }
        else {
            return wishListRepository.save(wishListData);
        }
    }

    @Override
    public List<WishListData> getAllWishListByUsername(String username) throws WishListDoesNotExistException {
        if (wishListRepository.findWishlistByUsername(username).isEmpty()) {
            throw new WishListDoesNotExistException("WishList Does Not Exist");
        }
        else {
            List<WishListData> wishListData = wishListRepository.findWishlistByUsername(username);
            List<WishListData> data1 = new ArrayList<>();
            data1.addAll(wishListData);
            return data1;
        }
    }

    @Override
    public String deleteWishList(String username, int id) throws WishListDoesNotExistException {
        Optional<WishListData> wishListData = wishListRepository.findByUserNameAndId(username,id);
        if (wishListData.isPresent()) {
            wishListRepository.deleteById(id);
            return "WishList Deleted Successfully";
        } else {
            throw new WishListDoesNotExistException("WishList Does Not Exist");

        }
    }

}
