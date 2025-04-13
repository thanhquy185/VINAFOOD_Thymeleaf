package vn.tuhoc.foodshop.service.specfication;

import org.springframework.data.jpa.domain.Specification;

import vn.tuhoc.foodshop.domain.Food;
import vn.tuhoc.foodshop.domain.Food_;

public class FoodSpecification {
    // Methods
    public static Specification<Food> idEqual(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Food_.ID), id);
    }

    public static Specification<Food> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Food_.NAME),"%" + name + "%");
    }

    public static Specification<Food> categoryEqual(String category) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Food_.CATEGORY).get("name"),
                category);
    }

    public static Specification<Food> statusEqual(Boolean status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Food_.STATUS), status);
    }
}
