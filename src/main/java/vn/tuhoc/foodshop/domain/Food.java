package vn.tuhoc.foodshop.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "foods")
public class Food {
    // Properties
    // - Primary
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // - Foreign
    @ManyToOne // Many foods to one category
    @JoinColumn(name = "categoryId")
    @JsonIgnoreProperties({ "name" })
    @NotNull(message = "Loại món ăn không được để trống !")
    private Category category;
    // - Others
    @NotBlank(message = "Tên món ăn không được để trống !")
    private String name;
    @NotNull(message = "Giá bán không được để trống !")
    @PositiveOrZero(message = "Giá bán phải là số nguyên lớn hơn hoặc bằng 0 !")
    private Long price;
    @NotNull(message = "Tồn kho không được để trống !")
    @PositiveOrZero(message = "Tồn kho phải là số nguyên lớn hơn hoặc bằng 0 !")
    private Long inventory;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;
    @NotNull(message = "Trạng thái không được để trống !")
    private Boolean status;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime timeUpdate = LocalDateTime.now();
    // - 
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL) // one food to many orderDetails
    @JsonIgnore
    List<CartDetails> cartDetails;
    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL) // one food to many orderDetails
    // @JsonManagedReference
    @JsonIgnore
    List<OrderDetails> orderDetails;

    // Constructors
    public Food() {

    }
    
    public Food(Long id, Category category, String name, Long inventory, Long price,
            String description, String image, Boolean status, LocalDateTime timeUpdate) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.inventory = inventory;
        this.price = price;
        this.description = description;
        this.image = image;
        this.status = status;
        this.timeUpdate = timeUpdate;
    }

    // Getter - Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(LocalDateTime timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public List<CartDetails> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetails> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    // toString
    @Override
    public String toString() {
        return "Food [id=" + id + ", category=" + category + ", name=" + name + ", inventory=" + inventory
                + ", price=" + price + ", description=" + description
                + ", image=" + image + ", status=" + status + ", timeUpdate=" + timeUpdate + ", ticketDetails="
                + "]";
    }

}
