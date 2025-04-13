package vn.tuhoc.foodshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tuhoc.foodshop.domain.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    // Methods
    // ArrayList<Order> findAll();
    Page<Order> findAll(Pageable pageable);
    Order findOneById(Long id);
    List<Order> findOrdersByCustomerId(Long customerId);
    // Order save(Order user);   // Nếu tồn tại thì UPDATE, ngược lại thì INSERT INTO
}
