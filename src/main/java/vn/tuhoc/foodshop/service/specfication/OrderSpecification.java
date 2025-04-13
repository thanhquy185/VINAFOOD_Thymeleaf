package vn.tuhoc.foodshop.service.specfication;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Expression;
import vn.tuhoc.foodshop.domain.Order;
import vn.tuhoc.foodshop.domain.Order_;

public class OrderSpecification {
    // Methods
    // Tìm kiếm theo mã đơn hàng
    public static Specification<Order> idEqual(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.ID), id);
    }

    // Tìm kiếm theo ngày tạo đơn
    public static Specification<Order> dateCreateEqual(String dateCreate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.function("DATE", String.class,
                root.get(Order_.TIME_CREATE)), dateCreate);
    }

    // Tìm kiếm theo tỉnh / thành
    public static Specification<Order> provinceLike(String province) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.RECEIVER_ADDRESS), "%" + province);
    }

    // Tìm kiếm theo quận / huyện
    public static Specification<Order> districtLike(String district) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Order_.RECEIVER_ADDRESS), "%" + district + "%");
    }

    // Tìm kiếm theo trạng thái
    public static Specification<Order> statusEqual(Integer status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Order_.STATUS), status);
    }
}
