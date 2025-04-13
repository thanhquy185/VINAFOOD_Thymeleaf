package vn.tuhoc.foodshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_details")
public class CartDetails {
    // Properties
    // - Primary
    @EmbeddedId
    private CartDetailsId id;
    @ManyToOne
    @MapsId("cartId")  // Ánh xạ với cartId trong cartDetailId
    @JsonBackReference  
    @JoinColumns({
        @JoinColumn(name = "cartId", referencedColumnName = "id")
    })
    private Cart cart;
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
    public CartDetails() {

    }
    
    public CartDetails(CartDetailsId id, Cart cart, Food food, Long quantity, Long price) {
        this.id = id;
        this.cart = cart;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter - Setter
    public CartDetailsId getId() {
        return id;
    }

    public void setId(CartDetailsId id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
