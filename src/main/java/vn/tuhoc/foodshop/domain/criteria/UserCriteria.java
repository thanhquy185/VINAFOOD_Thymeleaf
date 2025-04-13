package vn.tuhoc.foodshop.domain.criteria;

import java.util.Optional;

public class UserCriteria {
    // Properties
    private Optional<String> page;
    private Optional<String> id;
    private Optional<String> fullname;
    private Optional<String> sort;
    private Optional<String> phone;
    private Optional<String> role;
    private Optional<String> status;

    // Constructors
    public UserCriteria() {

    }

    public UserCriteria(Optional<String> page, Optional<String> id, Optional<String> fullname, Optional<String> sort,
            Optional<String> phone, Optional<String> role, Optional<String> status) {
        this.page = page;
        this.id = id;
        this.fullname = fullname;
        this.sort = sort;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

    // Getter - Setter
    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getFullname() {
        return fullname;
    }

    public void setFullname(Optional<String> fullname) {
        this.fullname = fullname;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

    public Optional<String> getPhone() {
        return phone;
    }

    public void setPhone(Optional<String> phone) {
        this.phone = phone;
    }

    public Optional<String> getRole() {
        return role;
    }

    public void setRole(Optional<String> role) {
        this.role = role;
    }

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }
    
}
