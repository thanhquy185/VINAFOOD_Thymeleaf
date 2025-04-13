package vn.tuhoc.foodshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.Category;
import vn.tuhoc.foodshop.domain.Order;
import vn.tuhoc.foodshop.domain.criteria.OrderCriteria;
import vn.tuhoc.foodshop.repository.OrderRepository;
import vn.tuhoc.foodshop.service.specfication.CategorySpecification;
import vn.tuhoc.foodshop.service.specfication.OrderSpecification;

@Service
public class OrderService {
    // Properties
    private final OrderRepository orderRepository;

    // Constructors
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Methods
    public Order getOneById(Long id) {
        return this.orderRepository.findOneById(id);
    }

    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    public List<Order> getAllByCustomerId(Long customerId) {
        return this.orderRepository.findOrdersByCustomerId(customerId);
    }

    public Page<Order> getAll(Pageable pageable, OrderCriteria orderCriteria) {
        //
        if (orderCriteria.getId() == null && orderCriteria.getDateCreate() == null && orderCriteria.getProvince() == null
                && orderCriteria.getDistrict() == null
                && orderCriteria.getStatus() == null) {
            return this.orderRepository.findAll(pageable);
        }

        //
        Specification<Order> combinedSpec = Specification.where(null);
        if (orderCriteria.getId() != null && orderCriteria.getId().isPresent()) {
            if (orderCriteria.getId().get().matches("\\d+")) {
                Specification<Order> currentSpec = OrderSpecification.idEqual(orderCriteria.getId().get());
                combinedSpec = combinedSpec.or(currentSpec);
            }
        }
        if (orderCriteria.getDateCreate() != null && orderCriteria.getDateCreate().isPresent()) {
            Specification<Order> currentSpec = OrderSpecification.dateCreateEqual(orderCriteria.getDateCreate().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }
        if (orderCriteria.getProvince() != null && orderCriteria.getProvince().isPresent()) {
            Specification<Order> currentSpec = OrderSpecification.provinceLike(orderCriteria.getProvince().get());
            combinedSpec = combinedSpec.and(currentSpec);
            System.out.println(orderCriteria.getProvince().get());
        }
        if (orderCriteria.getDistrict() != null && orderCriteria.getDistrict().isPresent()) {
            Specification<Order> currentSpec = OrderSpecification.districtLike(orderCriteria.getDistrict().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }
        if (orderCriteria.getStatus() != null && orderCriteria.getStatus().isPresent()) {
            String status = orderCriteria.getStatus().get();
            Specification<Order> currentSpec = OrderSpecification
                    .statusEqual(status.equals("Đã giao hàng") ? 3
                            : (status.equals("Đã xác nhận") ? 2 : (status.equals("Đã huỷ đơn") ? 1 : 0)));
            combinedSpec = combinedSpec.and(currentSpec);
            System.out.println(orderCriteria.getStatus().get());
        }

        return this.orderRepository.findAll(combinedSpec, pageable);
    }

    public Page<Order> getAll(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Order upsert(Order Order) {
        return this.orderRepository.save(Order);
    }
}
