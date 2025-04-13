package vn.tuhoc.foodshop.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderDetailsId implements Serializable {
    // Properties
    private Long orderId;
    private Long foodId;

    public OrderDetailsId() {}

    public OrderDetailsId(Long orderId, Long foodId) {
        this.orderId = orderId;
        this.foodId = foodId;
    }

    // Getter, Setter
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        OrderDetailsId that = (OrderDetailsId) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(foodId, that.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, foodId);
    }
}
