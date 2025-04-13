package vn.tuhoc.foodshop.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "carts")
public class Cart {
    // Properites
    // - Primary
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // - Others
    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
    @Min(value = 0)
    private Long totalPrice;
    // - Foreign
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<CartDetails> cartDetails;

    // Constructors
    public Cart() {
        
    }

    public Cart(Long id, User user, Long totalPrice, List<CartDetails> cartDetails) {
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.cartDetails = cartDetails;
    }

    // Getter - Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
