package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.CartDTO;
import com.swp391.webapp.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    // Service methods for Cart entity

    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll();
    }

    public CartDTO getCartById(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public CartDTO saveCart(CartDTO cart) {
        return cartRepository.save(cart);
    }

    public void deleteCart(int cartId) {
        cartRepository.deleteById(cartId);
    }

    // Additional service methods if needed
}
