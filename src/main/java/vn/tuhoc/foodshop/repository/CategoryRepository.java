package vn.tuhoc.foodshop.repository;


import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tuhoc.foodshop.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    // Methods
    // ArrayList<Category> findAll();
    Page<Category> findAll(Pageable pageable);
    Category findOneById(Long id);
    // Category save(Category user);   // Nếu tồn tại thì UPDATE, ngược lại thì INSERT INTO
    void deleteById(Long id);
}
