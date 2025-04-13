package vn.tuhoc.foodshop.domain.criteria;

import java.util.Optional;

public class ProductCriteria {
    // Properties
    private Optional<String> page;
    private Optional<String> name;
    private Optional<String> sort;
    private Optional<String> category;
    private Optional<String> status;

    // Constructors
    public ProductCriteria() {

    }

    public ProductCriteria(Optional<String> name, Optional<String> page, Optional<String> sort,
            Optional<String> category, Optional<String> status) {
        this.name = name;
        this.page = page;
        this.sort = sort;
        this.category = category;
        this.status = status;
    }

    // Methods
    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

    public Optional<String> getCategory() {
        return category;
    }

    public void setCategory(Optional<String> category) {
        this.category = category;
    }

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }

}
