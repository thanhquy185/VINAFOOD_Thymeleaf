package vn.tuhoc.foodshop.domain.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class CategoryLockDTO {
    // Properties
    @NotNull(message = "Trạng thái không được để trống !")
    private Boolean status;

    // Constructors
    public CategoryLockDTO() {

    }

    public CategoryLockDTO(Boolean status) {
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
