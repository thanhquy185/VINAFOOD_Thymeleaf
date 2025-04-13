package vn.tuhoc.foodshop.domain.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class FoodLockDTO {
    // Properties
    @NotNull(message = "Trạng thái không được để trống !")
    private Boolean status;

    // Constructors
    public FoodLockDTO() {

    }

    public FoodLockDTO(Boolean status) {
        this.status = status;
    }

    // Getter - Setter
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
