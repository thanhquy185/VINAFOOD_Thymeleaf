package vn.tuhoc.foodshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    // Properties
    // - Primary
    @EmbeddedId
    private OrderDetailsId id;
    @ManyToOne
    @MapsId("orderId")  // Ánh xạ với orderId trong OrderDetailId
    @JsonBackReference
    @JoinColumns({
        @JoinColumn(name = "orderId", referencedColumnName = "id")
    })
    private Order order;
    @ManyToOne
    @MapsId("foodId")  // Ánh xạ với foodId trong OrderDetailId
    // @JsonBackReference
    @JoinColumns({
        @JoinColumn(name = "foodId", referencedColumnName = "id")
    })
    private Food food;
    // - Others
    private Long quantity;
    private Long price;

    // Constructors
    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsId id, Order order, Food food, Long quantity, Long price) {
        this.id = id;
        this.order = order;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter, Setter
    public OrderDetailsId getId() {
        return id;
    }

    public void setId(OrderDetailsId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
}
