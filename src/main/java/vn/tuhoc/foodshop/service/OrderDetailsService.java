package vn.tuhoc.foodshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.OrderDetails;
import vn.tuhoc.foodshop.domain.OrderDetailsId;
import vn.tuhoc.foodshop.repository.OrderDetailsRepository;

@Service
public class OrderDetailsService {
    // Properties
    private final OrderDetailsRepository orderDetailRepository;

    // Constructors
    public OrderDetailsService(OrderDetailsRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    // Methods
    public OrderDetails getOneOrderDetailById(OrderDetailsId id) {
        return this.orderDetailRepository.findOneById(id);
    }

    public List<OrderDetails> getAllOrderDetail() {
        return this.orderDetailRepository.findAll();
    }

    public OrderDetails upsert(OrderDetails orderDetails) {
        return this.orderDetailRepository.save(orderDetails);
    }
}
