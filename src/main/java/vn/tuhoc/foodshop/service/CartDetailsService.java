package vn.tuhoc.foodshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.Cart;
import vn.tuhoc.foodshop.domain.CartDetails;
import vn.tuhoc.foodshop.domain.CartDetailsId;
import vn.tuhoc.foodshop.domain.Food;
import vn.tuhoc.foodshop.repository.CartDetailsRepository;

@Service
public class CartDetailsService {
    // Properties
    private final CartDetailsRepository cartDetailsRepository;

    // Constructors
    public CartDetailsService(CartDetailsRepository cartDetailsRepository) {
        this.cartDetailsRepository = cartDetailsRepository;
    }

    // Methods
    public CartDetails getOneCartDetailsByCartAndFood(Cart cart, Food food) {
        return this.cartDetailsRepository.findByCartAndFood(cart, food);
    }
    
    public CartDetails upsert(CartDetails cartDetails) {
        return this.cartDetailsRepository.save(cartDetails);
    }

    public void delete(CartDetails cartDetails) {
        this.cartDetailsRepository.delete(cartDetails);
    }

    public void deleteById(CartDetailsId id) {
        this.cartDetailsRepository.deleteById(id);
    }
}
