package com.cts.WishlistService.controller;

import com.cts.WishlistService.exception.StockExistAlreadyException;
import com.cts.WishlistService.exception.WishListDoesNotExistException;
import com.cts.WishlistService.model.WishListData;
import com.cts.WishlistService.repository.WishListRepository;
import com.cts.WishlistService.service.WishListStockService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/wishlist")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin("*")
public class WishListController {

    @Autowired
    private final WishListStockService wishListService;

    @Autowired
    WishListRepository  wishListRepository;

    @Autowired
    public WishListController(WishListStockService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping("/addWishlist/{username}")
    public ResponseEntity<?> saveWishListByUsername(@RequestBody WishListData wishListData) throws StockExistAlreadyException {
        Optional<WishListData> wishListData1 = wishListRepository.findByUserNameAndSymbol(wishListData.getUsername(), wishListData.getSymbol());
        try {
            if (wishListData1.isPresent()) {
                throw new StockExistAlreadyException("Stock Already Exist");
            } else {
                return new ResponseEntity<>(wishListService.saveWishListByUsername(wishListData), HttpStatus.OK);
            }
        } catch (StockExistAlreadyException e) {
            return new ResponseEntity<>("Stock Already Exist", HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/getWishlistByUsername/{username}")
    public ResponseEntity<?> getAllWishListByUsername(@PathVariable String username) throws WishListDoesNotExistException {
//        return new ResponseEntity<>(wishListService.getAllWishListByUsername(username), HttpStatus.OK);
        try {
            return new ResponseEntity<>(wishListService.getAllWishListByUsername(username), HttpStatus.OK);
        }catch (WishListDoesNotExistException e){
            return new ResponseEntity<>("WishList Does Not Exist", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/deleteWishlist")
    public ResponseEntity<?> deleteWishList(@RequestBody WishListData wishListData) throws WishListDoesNotExistException {
//        return new ResponseEntity<>(wishListService.deleteWishList(id), HttpStatus.OK);
        try {
            return new ResponseEntity<>(wishListService.deleteWishList(wishListData.getUsername(),wishListData.getWishlistId()), HttpStatus.OK);
        }catch (WishListDoesNotExistException e){
            return new ResponseEntity<>("WishList Does Not Exist", HttpStatus.NOT_FOUND);
        }
    }

}
