package vn.tuhoc.foodshop.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginDTO {
    // Properties
    @NotBlank(message = "Tên tài khoản không được để trống !")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống !")
    private String password;

    // Constructors
    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
