package vn.tuhoc.foodshop.service;

import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.Cart;
import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.repository.CartRepository;

@Service
public class CartService {
    // Properties
    private final CartRepository cartRepository;

    // Constructors
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Methods
    public Cart getOneByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public Cart upsert(Cart cart) {
        return this.cartRepository.save(cart);
    }

    public void delete(Cart cart) {
        this.cartRepository.delete(cart);
    }
}
