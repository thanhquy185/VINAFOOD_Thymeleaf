package vn.tuhoc.foodshop.domain.dto;

import jakarta.validation.constraints.NotNull;

public class UserLockDTO {
    // Properties
    @NotNull(message = "Trạng thái không được để trống !")
    private Boolean status;

    // Constructors
    public UserLockDTO() {

    }

    public UserLockDTO(Boolean status) {
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
