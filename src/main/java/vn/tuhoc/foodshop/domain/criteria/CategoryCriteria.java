package vn.tuhoc.foodshop.domain.criteria;

import java.util.Optional;

public class CategoryCriteria {
    // Properties
    private Optional<String> page;
    private Optional<String> id;
    private Optional<String> name;
    private Optional<String> sort;
    private Optional<String> status;

    // Constructors
    public CategoryCriteria() {

    }

    public CategoryCriteria(Optional<String> page, Optional<String> id, Optional<String> name, Optional<String> sort,
            Optional<String> status) {
        this.page = page;
        this.id = id;
        this.name = name;
        this.sort = sort;
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

    public Optional<String> getStatus() {
        return status;
    }

    public void setStatus(Optional<String> status) {
        this.status = status;
    }
    
}
