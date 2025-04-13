package vn.tuhoc.foodshop.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tuhoc.foodshop.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    // Methods
    // ArrayList<User> findAll();
    Page<User> findAll(Pageable page);
    // Page<User> findAll(Specification<User> spec, Pageable page);
    User findOneById(Long id);
    User findOneByUsername(String username);
    // User save(User user);   // Nếu tồn tại thì UPDATE, ngược lại thì INSERT INTO
    void deleteById(Long id);
}
