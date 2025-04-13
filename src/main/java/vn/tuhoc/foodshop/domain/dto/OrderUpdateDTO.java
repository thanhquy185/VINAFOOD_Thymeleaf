package vn.tuhoc.foodshop.domain.dto;

import jakarta.persistence.Column;

public class OrderUpdateDTO {
    // Properties
    @Column(columnDefinition = "TINYINT(3)")
    private Integer status;

    // Constructors
    public OrderUpdateDTO(Integer status) {
        this.status = status;
    }

    // Getter - Setter
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
}
