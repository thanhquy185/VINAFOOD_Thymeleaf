package vn.tuhoc.foodshop.service.specfication;

import org.springframework.data.jpa.domain.Specification;

import vn.tuhoc.foodshop.domain.Role_;
import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.domain.User_;

public class UserSpecification {
    // Tìm kiếm theo mã người dùng
    public static Specification<User> idEqual(String id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.ID), id);
    }

    // Tìm kiếm theo họ và tên
    public static Specification<User> fullnameLike(String fullname) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(User_.FULLNAME), "%" + fullname + "%");
    }

    // Tìm kiếm theo số điện thoại
    public static Specification<User> phoneEqual(String phone) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.PHONE), phone);
    }

    // Tìm theo theo phân quyền
    public static Specification<User> roleEqual(String role) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.ROLE).get("name"),
                role);
    }

    // Tìm theo theo trạng thái
    public static Specification<User> statusEqual(Boolean status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.STATUS),
                status);
    }
}
