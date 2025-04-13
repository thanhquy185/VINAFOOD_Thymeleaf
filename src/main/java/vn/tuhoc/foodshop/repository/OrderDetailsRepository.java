package vn.tuhoc.foodshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.tuhoc.foodshop.domain.OrderDetails;
import vn.tuhoc.foodshop.domain.OrderDetailsId;


@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId>, JpaSpecificationExecutor<OrderDetails> {
    // Methods
    // ArrayList<OrderDetail> findAll();
    Page<OrderDetails> findAll(Pageable pageable);
    OrderDetails findOneById(OrderDetailsId id);
    // OrderDetail save(OrderDetail user);   // Nếu tồn tại thì UPDATE, ngược lại thì INSERT INTO
}
