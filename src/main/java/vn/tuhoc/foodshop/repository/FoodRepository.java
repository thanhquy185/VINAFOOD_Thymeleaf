package vn.tuhoc.foodshop.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tuhoc.foodshop.domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>, JpaSpecificationExecutor<Food> {
    // Methods
    // ArrayList<Food> findAll();
    Page<Food> findAll(Pageable pageable);
    Food findOneById(Long id);
    // Food save(Food user);   // Nếu tồn tại thì UPDATE, ngược lại thì INSERT INTO
    void deleteById(Long id);
}

