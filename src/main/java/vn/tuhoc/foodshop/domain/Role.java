package vn.tuhoc.foodshop.domain;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Role {
    // Properties
    // - Primary
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // - Others
    private String name;
    private Boolean status;
    private Date dateUpdate;
    // - 
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL) // one role to many users
    @JsonIgnore
    List<User> users;

    // Constructors
    public Role() {

    }

    public Role(Integer id, String name, Boolean status, Date dateUpdate, List<User> users) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.dateUpdate = dateUpdate;
        this.users = users;
    }

    // Getter - Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // toString
    @Override
    public String toString() {
        return "Role [id=" + id + ", name=" + name + ", status=" + status + ", dateUpdate=" + dateUpdate + "]";
    }

    
}
