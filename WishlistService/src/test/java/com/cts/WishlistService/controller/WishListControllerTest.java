package com.cts.WishlistService.controller;

import com.cts.WishlistService.exception.StockExistAlreadyException;
import com.cts.WishlistService.exception.WishListDoesNotExistException;
import com.cts.WishlistService.model.WishListData;
import com.cts.WishlistService.repository.WishListRepository;
import com.cts.WishlistService.service.WishListStockService;
import com.cts.WishlistService.serviceImpl.WishListStockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

class WishListControllerTest {

    @Mock
    private WishListStockService wishListService;

    @Mock
    private WishListRepository wishListRepository;

    @InjectMocks
    private WishListController wishListController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetAllWishListByUsername_Success() throws WishListDoesNotExistException {
        String username = "username";
        List<WishListData> wishListDataList = new ArrayList<>();
        wishListDataList.add(new WishListData(1,username, "symbol","Name","currency", "exchange", "mic", "india","type"));
        wishListDataList.add(new WishListData(2,username, "symbol1","Name","currency", "exchange", "mic", "india","type"));

        Mockito.when(wishListService.getAllWishListByUsername(anyString())).thenReturn(wishListDataList);

        ResponseEntity<?> responseEntity = wishListController.getAllWishListByUsername(username);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(wishListDataList, responseEntity.getBody());
    }

    @Test
    void testGetAllWishListByUsername_WishListDoesNotExistException() throws WishListDoesNotExistException {
        String username = "username";

        Mockito.when(wishListService.getAllWishListByUsername(anyString()))
                .thenThrow(new WishListDoesNotExistException("WishList Does Not Exist"));

        ResponseEntity<?> responseEntity = wishListController.getAllWishListByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("WishList Does Not Exist", responseEntity.getBody());
    }

    @Test
    void testDeleteWishList_Success() throws WishListDoesNotExistException {
        WishListData wishListData = new WishListData(1,"username", "symbol","Name","currency", "exchange", "mic", "india","type");

        Mockito.when(wishListService.deleteWishList("username", 1)).thenReturn("WishList Deleted Successfully");

        ResponseEntity<?> responseEntity = wishListController.deleteWishList(wishListData);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("WishList Deleted Successfully", responseEntity.getBody());
    }

    @Test
    void testDeleteWishList_WishListDoesNotExistException() throws WishListDoesNotExistException {
        WishListData wishListData = new WishListData(1,"username", "symbol","Name","currency", "exchange", "mic", "india","type");

        Mockito.when(wishListService.deleteWishList(wishListData.getUsername(), wishListData.getWishlistId()))
                .thenThrow(new WishListDoesNotExistException("WishList Does Not Exist"));

        ResponseEntity<?> responseEntity = wishListController.deleteWishList(wishListData);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("WishList Does Not Exist", responseEntity.getBody());
    }


}

