package vn.tuhoc.foodshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.tuhoc.foodshop.domain.Cart;
import vn.tuhoc.foodshop.domain.User;
import java.util.List;



public interface CartRepository extends JpaRepository<Cart, Long>, JpaSpecificationExecutor<Cart> {
    // Methods
    Cart findByUser(User user);
}
