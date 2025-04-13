package vn.tuhoc.foodshop.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryUpdateDTO {
    // Properties
    @NotBlank(message = "Tên loại món ăn không được để trống !")
    private String name;

    // Constructors
    public CategoryUpdateDTO() {
    }

    public CategoryUpdateDTO(String name) {
        this.name = name;
    }

    // Getter - Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
