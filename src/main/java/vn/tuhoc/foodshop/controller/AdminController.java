package vn.tuhoc.foodshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.tuhoc.foodshop.service.CategoryService;
import vn.tuhoc.foodshop.service.FoodService;
import vn.tuhoc.foodshop.service.OrderService;
import vn.tuhoc.foodshop.service.RoleService;
import vn.tuhoc.foodshop.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    // Properties
    private final OrderService orderService;
    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final FoodService foodService;

    // Constructors
    public AdminController(OrderService orderService, UserService userService, RoleService roleService,
            CategoryService categoryService,
            FoodService foodService) {
        this.orderService = orderService;
        this.userService = userService;
        this.roleService = roleService;
        this.categoryService = categoryService;
        this.foodService = foodService;
    }

    // Methods
    @GetMapping("/home")
    public String getAdminPage(Model model) {
        model.addAttribute("page", "home");
        return "layouts/admin-layout";
        // return "fragments/admin/home :: content";
    }

    // Methods
    @GetMapping("/dashboard-shop")
    public String getDashboardShopPage(Model model) {
        model.addAttribute("page", "dashboard-shop");
        return "layouts/admin-layout";
    }

}
