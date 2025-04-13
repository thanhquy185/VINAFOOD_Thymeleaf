package vn.tuhoc.foodshop.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity // Đại diện cho một bảng trong csdl
@Table(name = "users") // Đổi tên khi thêm vào csdl
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class User {
    // Properties
    // - Primary
    @Id // Khi sử dụng @Entity buộc phải khai báo để biết đó là khoá chính trong csdl
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Đảm bảo giá trị tự động tăng (auto increment)
    private long id;
    // - Foreign
    @ManyToOne // Many users to one role
    @JoinColumn(name = "roleId") // Đảm bảo kết với bảng roles là thuộc tính roleId
    // @JsonBackReference
    @JsonIgnoreProperties({ "status", "timeUpdate" })
    @NotNull(message = "Phân quyền không được để trống !")
    private Role role;
    // - Others
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime timeCreate = LocalDateTime.now();
    @NotBlank(message = "Tên tài khoản không được để trống !")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Mật khẩu không được để trống !")
    private String password;
    @NotBlank(message = "Họ và tên không được để trống !")
    private String fullname;
    @Column(columnDefinition = "DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;
    private Boolean gender;
    @NotBlank(message = "Số điện thoại không được để trống !")
    @Column(columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
    @Pattern(regexp = "^(\\d{10}|\\d{11})$", message = "Số điện thoại chỉ chứa chữ số và có 10 hoặc 11 số !")
    private String phone;
    // @NotBlank(message = "Email không được để trống !")
    // @Email(message = "Định dạng email không hợp lệ !", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Địa chỉ không được để trống !")
    private String address;
    private String image;
    @NotNull(message = "Trạng thái không được để trống !")
    private Boolean status;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime timeUpdate = LocalDateTime.now();
    // -
    @OneToOne(mappedBy = "user") // one user to one cart
    private Cart cart;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL) // one employee to many orders
    @JsonIgnore
    List<Order> ordersFromEmployee;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // one customer to many orders
    @JsonIgnore
    List<Order> ordersFromCustomer;

    // Constructors
    public User() {

    }

    public User(long id, Role role, LocalDateTime timeCreate, String username, String password, String fullname,
            LocalDate birthday, Boolean gender, String phone, String email, String address, String image,
            Boolean status, LocalDateTime timeUpdate, Cart cart) {
        this.id = id;
        this.role = role;
        this.timeCreate = timeCreate;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.image = image;
        this.status = status;
        this.timeUpdate = timeUpdate;
        this.cart = cart;
    }

    // Getter - Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrdersFromEmployee() {
        return ordersFromEmployee;
    }

    public void setOrdersFromEmployee(List<Order> ordersFromEmployee) {
        this.ordersFromEmployee = ordersFromEmployee;
    }

    public List<Order> getOrdersFromCustomer() {
        return ordersFromCustomer;
    }

    public void setOrdersFromCustomer(List<Order> ordersFromCustomer) {
        this.ordersFromCustomer = ordersFromCustomer;
    }

}
