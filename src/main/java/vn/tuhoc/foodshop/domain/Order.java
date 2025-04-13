package vn.tuhoc.foodshop.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "id")
public class Order {
    // Properties
    // - Primary
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // - Foreign
    @ManyToOne // Many orders to one employee
    @JoinColumn(name = "employeeId")
    @JsonIgnoreProperties({ "role", "username", "password", "phone", "email", "address", "status",
            "dateUpdate" })
    private User employee;
    @ManyToOne // Many orders to one customer
    @JoinColumn(name = "customerId")
    @JsonIgnoreProperties({ "role", "username", "password", "address", "status",
            "dateUpdate" })
    private User customer;
    // - Others
    @Column(columnDefinition = "DATETIME")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeCreate = LocalDateTime.now();
    @Column(columnDefinition = "TEXT")
    private String payMethod;
    private String receiverFullname;
    private String receiverPhone;
    @Column(columnDefinition = "TEXT")
    private String receiverAddress;
    private Long totalPrice;
    @Column(columnDefinition = "TINYINT(3)")
    private Integer status = 0;
    // -
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // one order to many orderDetails
    @JsonManagedReference
    List<OrderDetails> orderDetails;

    // Constructors
    public Order() {

    }

    public Order(Long id, User employee, User customer, LocalDateTime timeCreate, String payMethod, String receiverFullname, String receiverPhone,
            String receiverAddress, Long totalPrice, Integer status) {
        this.id = id;
        this.employee = employee;
        this.customer = customer;
        this.payMethod = payMethod;
        this.timeCreate = timeCreate;
        this.receiverFullname = receiverFullname;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getter - Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getReceiverFullname() {
        return receiverFullname;
    }

    public void setReceiverFullname(String receiverFullname) {
        this.receiverFullname = receiverFullname;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
