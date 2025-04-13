package vn.tuhoc.foodshop.domain.criteria;

import java.util.Optional;

public class OrderCriteria {
     // Properties
    private Optional<String> page;
    private Optional<String> id;
    private Optional<String> sort;
    private Optional<String> dateCreate;
    private Optional<String> province;
    private Optional<String> district;
    private Optional<String> status;

    // Constructors
    public OrderCriteria(Optional<String> page, Optional<String> id, Optional<String> sort, Optional<String> dateCreate,
            Optional<String> province, Optional<String> district, Optional<String> status) {
        this.page = page;
        this.id = id;
        this.sort = sort;
        this.dateCreate = dateCreate;
        this.province = province;
        this.district = district;
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

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

    public Optional<String> getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Optional<String> dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Optional<String> getProvince() {
        return province;
    }

    public void setProvince(Optional<String> province) {
        this.province = province;
    }

    public Optional<String> getDistrict() {
        return district;
    }

    public void setDistrict(Optional<String> district) {
        this.district = district;
    }

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }

    
}
