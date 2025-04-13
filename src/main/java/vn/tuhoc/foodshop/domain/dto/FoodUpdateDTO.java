package vn.tuhoc.foodshop.domain.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import vn.tuhoc.foodshop.domain.Category;

public class FoodUpdateDTO {
    // Properties
    @NotNull(message = "Loại món ăn không được để trống !")
    private Category category;
    @NotBlank(message = "Tên món ăn không được để trống !")
    private String name;
    @Column(columnDefinition = "LONG")
    @NotNull(message = "Giá bán không được để trống !")
    @PositiveOrZero(message = "Giá bán phải là số nguyên lớn hơn hoặc bằng 0 !")
    private Long price;
    @Column(columnDefinition = "LONG")
    @NotNull(message = "Tồn kho không được để trống !")
    @PositiveOrZero(message = "Tồn kho phải là số nguyên lớn hơn hoặc bằng 0 !")
    private Long inventory;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String image;

    // Constructors
    public FoodUpdateDTO() {
    }

    public FoodUpdateDTO(Category category, String name, Long price,
            Long inventory, String description, String image) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.description = description;
        this.image = image;
    }

    // Setter - Getter
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
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
}
