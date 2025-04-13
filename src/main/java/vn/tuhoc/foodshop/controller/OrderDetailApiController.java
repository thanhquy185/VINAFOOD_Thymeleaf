package vn.tuhoc.foodshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.tuhoc.foodshop.domain.OrderDetails;
import vn.tuhoc.foodshop.domain.OrderDetailsId;
import vn.tuhoc.foodshop.service.OrderDetailsService;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailApiController {
    // Properties
    private final OrderDetailsService orderDetailService;

    // Constructors
    public OrderDetailApiController(OrderDetailsService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<?>> listOrderDetail(Model model) {
        List<OrderDetails> listOrderDetail = this.orderDetailService.getAllOrderDetail();
        return ResponseEntity.status(HttpStatus.OK).body(listOrderDetail);
    }

    @GetMapping("/detail/{orderId}-{productId}")
    public ResponseEntity<OrderDetails> detailOrderDetail(@PathVariable("orderId") Long orderId,
            @PathVariable("productId") Long productId) {
        OrderDetailsId id = new OrderDetailsId(orderId, productId);
        OrderDetails orderDetailSelected = this.orderDetailService.getOneOrderDetailById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderDetailSelected);
    }
}
