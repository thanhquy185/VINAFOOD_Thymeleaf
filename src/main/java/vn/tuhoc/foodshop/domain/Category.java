package vn.tuhoc.foodshop.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "categories")
public class Category {
    // Properties
    // - Primary
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // - Others
    @NotBlank(message = "Tên loại món ăn không được để trống !")
    private String name;
    @NotNull(message = "Trạng thái không được để trống !")
    private Boolean status;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime timeUpdate = LocalDateTime.now();
    // -
    @OneToMany(mappedBy = "category") // one category to many foods
    @JsonIgnore
    List<Food> foods;

    // Constructors
    public Category() {

    }

    public Category(Long id, String name, Boolean status, LocalDateTime timeUpdate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.timeUpdate = timeUpdate;
    }

    // Getter - Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    // toString
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", status=" + status + ", timeUpdate=" + timeUpdate
                + "]";
    }
}
