package vn.tuhoc.foodshop.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartDetailsId implements Serializable {
    // Properties
    private Long cartId;
    private Long foodId;

    // Constructors
    public CartDetailsId() {}

    public CartDetailsId(Long cartId, Long foodId) {
        this.cartId = cartId;
        this.foodId = foodId;
    }

    // Getter, Setter
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    // hashCode() & equals() đảm bảo đúng logic khóa chính
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetailsId that = (CartDetailsId) o;
        return Objects.equals(cartId, that.cartId) &&
               Objects.equals(foodId, that.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, foodId);
    }
}
