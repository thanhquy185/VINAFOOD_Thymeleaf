package vn.tuhoc.foodshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.tuhoc.foodshop.domain.Cart;
import vn.tuhoc.foodshop.domain.CartDetails;
import vn.tuhoc.foodshop.domain.CartDetailsId;
import vn.tuhoc.foodshop.domain.Food;



public interface CartDetailsRepository extends JpaRepository<CartDetails, CartDetailsId>, JpaSpecificationExecutor<CartDetails> {
    // Methods
    CartDetails findByCartAndFood(Cart cart, Food food);
}
