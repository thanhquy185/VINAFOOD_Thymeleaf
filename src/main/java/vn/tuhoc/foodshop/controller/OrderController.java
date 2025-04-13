package vn.tuhoc.foodshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import vn.tuhoc.foodshop.domain.Food;
import vn.tuhoc.foodshop.domain.Order;
import vn.tuhoc.foodshop.domain.OrderDetails;
import vn.tuhoc.foodshop.domain.Order_;
import vn.tuhoc.foodshop.domain.criteria.OrderCriteria;
import vn.tuhoc.foodshop.service.FoodService;
import vn.tuhoc.foodshop.service.OrderService;
import vn.tuhoc.foodshop.service.UserService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {
    // Properties
    private final UserService userService;
    private final OrderService orderService;
    private final FoodService foodService;

    // Constructors
    public OrderController(UserService userService, OrderService orderService, FoodService foodService) {
        this.userService = userService;
        this.orderService = orderService;
        this.foodService = foodService;
    }

    // Methods
    @GetMapping("")
    public String getOrdersPage(Model model, OrderCriteria orderCriteria, HttpServletRequest request) {
        // Đảm bảo page mặc định là 1 dù người dùng có tự nhập
        int page = 1;
        try {
            if (orderCriteria.getPage().isPresent()) {
                page = Integer.parseInt(orderCriteria.getPage().get());
            } else {

            }
        } catch (Exception e) {

        }
        // Tuỳ theo tiêu chí sắp xếp mà trả về dữ liệu (mặc định là ID tăng dần)
        Pageable pageable = PageRequest.of(page - 1, 9);
        ;
        if (orderCriteria.getSort() != null && orderCriteria.getSort().isPresent()) {
            String sort = orderCriteria.getSort().get();
            if (sort.equals("ID tăng dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Order_.ID).ascending());
            } else if (sort.equals("ID giảm dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Order_.ID).descending());
            } else if (sort.equals("Ngày tạo đơn tăng dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Order_.TIME_CREATE).ascending());
            } else if (sort.equals("Ngày tạo đơn giảm dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Order_.TIME_CREATE).descending());
            } else if (sort.equals("Tổng thanh toán tăng dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Order_.TOTAL_PRICE).ascending());
            } else if (sort.equals("Tổng thanh toán giảm dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Order_.TOTAL_PRICE).descending());
            }
        }
        // Cập nhật dữ liệu và chức năng phân trang
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            queryString = queryString.replace("page=" + page, "");
        }
        Page<Order> pageOrder = this.orderService.getAll(pageable, orderCriteria);
        List<Order> listOrder = pageOrder.getContent();

        // Thêm thuộc tính cần thiết
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageOrder.getTotalPages());
        model.addAttribute("queryString", queryString);
        model.addAttribute("orders", listOrder);
        model.addAttribute("page", "/order/list-order");

        return "layouts/admin-layout";
    }

    @GetMapping("/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("order", this.orderService.getOneById(id));
        model.addAttribute("page", "/order/update-order");

        return "layouts/admin-layout";
    }

    @PostMapping("/update/{id}")
    public String handleUpdateOrder(@PathVariable("id") Long id, @RequestParam("action") String action) {
        Order orderUpdate = this.orderService.getOneById(id);
        if (orderUpdate != null) {
            if(orderUpdate.getStatus() == 0) {
                orderUpdate.setEmployee(this.userService.getUserLogin());
            }
            switch (action) {
                case "ship":
                    orderUpdate.setStatus(3);
                    break;
                case "confirm":
                    for(OrderDetails orderDetails : orderUpdate.getOrderDetails()) {
                        Food food = orderDetails.getFood();
                        food.setInventory(food.getInventory() - orderDetails.getQuantity());
                        this.foodService.upsert(food);
                    }
                    orderUpdate.setStatus(2);
                    break;
                case "cancel":
                    orderUpdate.setStatus(1);
                    break;
            }
            this.orderService.upsert(orderUpdate);
        }

        return "redirect:/admin/orders";
    }

    @GetMapping("/print/{id}")
    public String getPrintOrderPage(Model model, @PathVariable("id") Long id) {
        // Định dạng lại ngày in phiếu hiện tại
        LocalDate currentDate = LocalDate.now();
        String currentDateFormat = String.format("Ngày %d tháng %d năm %d", currentDate.getDayOfMonth(),
                currentDate.getMonthValue(), currentDate.getYear());

        //
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("currentDate", currentDateFormat);
        model.addAttribute("order", this.orderService.getOneById(id));
        model.addAttribute("page", "/order/print-order");

        return "layouts/admin-layout";
    }

}
