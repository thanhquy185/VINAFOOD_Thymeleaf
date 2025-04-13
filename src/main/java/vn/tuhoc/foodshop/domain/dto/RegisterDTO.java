package vn.tuhoc.foodshop.domain.dto;

import jakarta.validation.constraints.NotBlank;

public class RegisterDTO {
    // Properties
    @NotBlank(message = "Tên tài khoản không được để trống !")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống !")
    private String password;
    @NotBlank(message = "Xác nhận mật khẩu không được để trống !")
    private String confirmPassword;
    
    // Constructors
    public RegisterDTO(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    // Getter - Setter
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
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    } 

    
}
