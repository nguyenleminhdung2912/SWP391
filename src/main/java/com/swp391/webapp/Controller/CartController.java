package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.CartDTO;
import com.swp391.webapp.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable int cartId) {
        CartDTO cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping
    public ResponseEntity<CartDTO> saveCart(@RequestBody CartDTO cart) {
        CartDTO savedCart = cartService.saveCart(cart);
        return ResponseEntity.ok(savedCart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
