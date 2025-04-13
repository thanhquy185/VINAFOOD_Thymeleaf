package vn.tuhoc.foodshop.domain.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import vn.tuhoc.foodshop.domain.Role;

public class UserUpdateDTO {
    // Properites
    @NotNull(message = "Phân quyền không được để trống !")
    private Role role;
    // @NotBlank(message = "Mật khẩu không được để trống !")
    private String password;
    @NotBlank(message = "Họ và tên không được để trống !")
    private String fullname;
    @Column(columnDefinition = "DATE")
    private LocalDate birthday;
    private Boolean gender;
    @NotBlank(message = "Số điện thoại không được để trống !")
    @Column(columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    @Pattern(regexp = "^(\\d{10}|\\d{11})$", message = "Số điện thoại chỉ chứa chữ số và có 10 hoặc 11 số !")
    private String phone;
    // @Email(message = "Định dạng email không hợp lệ !", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String address;
    private String image;

    // Constructors
    public UserUpdateDTO() {
        
    }

    public UserUpdateDTO(Role role, String password, String fullname, LocalDate birthday,
            Boolean gender, String phone, String email, String address, String image) {
        this.role = role;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
    }

    // Getter - Setter
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
}
