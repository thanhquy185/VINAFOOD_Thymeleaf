package vn.tuhoc.foodshop.service.specfication;

import org.springframework.data.jpa.domain.Specification;

import vn.tuhoc.foodshop.domain.Category;
import vn.tuhoc.foodshop.domain.Category_;

public class CategorySpecification {
    // Methods
    public static Specification<Category> idEqual(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Category_.ID), id);
    }

    public static Specification<Category> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Category_.NAME),"%" + name + "%");
    }

    public static Specification<Category> statusEqual(Boolean status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Category_.STATUS), status);
    }
}
