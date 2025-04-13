package vn.tuhoc.foodshop.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import vn.tuhoc.foodshop.domain.Cart;
import vn.tuhoc.foodshop.domain.CartDetails;
import vn.tuhoc.foodshop.domain.Food;
import vn.tuhoc.foodshop.domain.Food_;
import vn.tuhoc.foodshop.domain.Order;
import vn.tuhoc.foodshop.domain.OrderDetails;
import vn.tuhoc.foodshop.domain.OrderDetailsId;
import vn.tuhoc.foodshop.domain.Role;
import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.domain.criteria.ProductCriteria;
import vn.tuhoc.foodshop.domain.dto.OrderUpdateDTO;
import vn.tuhoc.foodshop.domain.dto.UserUpdateDTO;
import vn.tuhoc.foodshop.service.CartDetailsService;
import vn.tuhoc.foodshop.service.CartService;
import vn.tuhoc.foodshop.service.CategoryService;
import vn.tuhoc.foodshop.service.FoodService;
import vn.tuhoc.foodshop.service.OrderDetailsService;
import vn.tuhoc.foodshop.service.OrderService;
import vn.tuhoc.foodshop.service.UploadService;
import vn.tuhoc.foodshop.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/client")
public class ClientController {
    // Properties
    private final UploadService uploadService;
    private final FoodService foodService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CartService cartService;
    private final CartDetailsService cartDetailsService;
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;

    // Contructors
    public ClientController(UploadService uploadService, FoodService foodService, CategoryService categoryService,
            UserService userService,
            CartService cartService, CartDetailsService cartDetailsService, OrderService orderService,
            OrderDetailsService orderDetailsService) {
        this.uploadService = uploadService;
        this.foodService = foodService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.cartService = cartService;
        this.cartDetailsService = cartDetailsService;
        this.orderService = orderService;
        this.orderDetailsService = orderDetailsService;
    }

    // Methods
    @GetMapping("/product")
    public String getProductPage(Model model, ProductCriteria productCriteria, HttpServletRequest request) {
        // Đảm bảo page mặc định là 1 dù người dùng có tự nhập
        int page = 1;
        try {
            if (productCriteria.getPage().isPresent()) {
                page = Integer.parseInt(productCriteria.getPage().get());
            } else {

            }
        } catch (Exception e) {

        }

        //
        Pageable pageable = PageRequest.of(page - 1, 9);
        if (productCriteria.getSort() != null && productCriteria.getSort().isPresent()) {
            String sort = productCriteria.getSort().get();
            if (sort.equals("Tên món ăn tăng dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Food_.NAME).ascending());
            } else if (sort.equals("Tên món ăn giảm dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Food_.NAME).descending());
            } else if (sort.equals("Giá bán tăng dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Food_.PRICE).ascending());
            } else if (sort.equals("Giá bán giảm dần")) {
                pageable = PageRequest.of(page - 1, 9, Sort.by(Food_.PRICE).descending());
            }
        }

        //
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            queryString = queryString.replace("page=" + page, "");
        }
        Page<Food> pageFood = this.foodService.getAll(pageable, productCriteria);
        // Role roleUserLogin = this.userService.getUserLogin().getRole();

        //
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageFood.getTotalPages());
        model.addAttribute("foods", pageFood.getContent());
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("queryString", queryString);
        model.addAttribute("page", "/product");

        return "layouts/client-layout";
    }

    @PostMapping("/product/add-to-cart/{id}")
    public String handleAddToCartAtProductPage(@PathVariable("id") Long id) {
        Food food = this.foodService.getOneById(id);
        User user = this.userService.getUserLogin();
        if (food != null && user != null) {
            this.foodService.addToCart(food, user, new Long("1"));
        }

        return "redirect:/client/product";
    }

    @GetMapping("/product/detail/{id}")
    public String getProductPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("food", this.foodService.getOneById(id));
        model.addAttribute("page", "/detail-product");
        return "layouts/client-layout";
    }

    @PostMapping("/product/detail/add-to-cart/{id}")
    public String handleAddToCartAtDetailProductPage(@PathVariable("id") Long id,
            @RequestParam("quantity") Long quantity) {
        Food food = this.foodService.getOneById(id);
        User user = this.userService.getUserLogin();
        if (food != null && user != null) {
            this.foodService.addToCart(food, user, quantity);
        }

        return "redirect:/client/product/detail/" + String.valueOf(id);
    }

    @GetMapping("/info")
    public String getInfoPage(Model model) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("page", "/info");
        return "layouts/client-layout";
    }

    @PostMapping("/info")
    public String postMethodName(@ModelAttribute("userLogin") @Valid UserUpdateDTO user,
            @RequestParam("file-image") MultipartFile fileImage) {
        User userUpdate = this.userService.getUserLogin();
        if (userUpdate != null) {
            // Cập nhật file ảnh vào source code và lấy ra tên file để lưu vào csdl
            String image = null;
            if (fileImage != null && !fileImage.isEmpty()) {
                image = this.uploadService.uploadImageFiles(fileImage, "users", String.valueOf(userUpdate.getId()));
            }
            user.setImage(image);

            // Mã hoá mật khẩu
            // String hashPassword = this.passwordEncoder.encode(user.getPassword());
            // user.setPassword(hashPassword);

            // Cập nhật dữ liệu đã thay đổi
            if (userUpdate.getImage() == null || user.getImage() != null) {
                userUpdate.setImage(user.getImage());
            }
            // userUpdate.setPassword(user.getPassword());
            userUpdate.setRole(user.getRole());
            userUpdate.setFullname(user.getFullname());
            userUpdate.setBirthday(user.getBirthday());
            userUpdate.setGender(user.getGender());
            userUpdate.setPhone(user.getPhone());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setAddress(user.getAddress());
            userUpdate.setTimeUpdate(LocalDateTime.now());
            this.userService.upsertUser(userUpdate);
        }

        return "redirect:/client/info";
    }

    @GetMapping("/shopping")
    public String getShoppingPage(Model model) {
        List<Order> listOrder = this.orderService.getAllByCustomerId(this.userService.getUserLogin().getId());
        Cart cart = this.cartService.getOneByUser(this.userService.getUserLogin());

        model.addAttribute("cart", cart);
        model.addAttribute("orders", listOrder);
        model.addAttribute("totalOrders", listOrder.size());
        model.addAttribute("page", "/shopping");

        return "layouts/client-layout";
    }

    @PostMapping("/shopping/handle-cart/{foodId}")
    public String handleProductInCart(@PathVariable("foodId") Long foodId, @RequestParam("action") String action) {
        Cart cart = this.cartService.getOneByUser(this.userService.getUserLogin());
        Food food = this.foodService.getOneById(foodId);
        if (cart != null && food != null) {
            CartDetails cartDetailUpdate = this.cartDetailsService.getOneCartDetailsByCartAndFood(cart, food);
            switch (action) {
                case "plus":
                    if (cartDetailUpdate.getQuantity() >= 0) {
                        cart.setTotalPrice(cart.getTotalPrice() + food.getPrice());
                        this.cartService.upsert(cart);

                        cartDetailUpdate.setQuantity(cartDetailUpdate.getQuantity() + 1);
                        this.cartDetailsService.upsert(cartDetailUpdate);
                    }

                    break;
                case "minus":
                    if (cartDetailUpdate.getQuantity() >= 2) {
                        cart.setTotalPrice(cart.getTotalPrice() - food.getPrice());
                        this.cartService.upsert(cart);

                        cartDetailUpdate.setQuantity(cartDetailUpdate.getQuantity() - 1);
                        this.cartDetailsService.upsert(cartDetailUpdate);
                    }

                    break;
                case "remove":
                    cart.setTotalPrice(
                            cart.getTotalPrice() - cartDetailUpdate.getPrice() * cartDetailUpdate.getQuantity());
                    this.cartService.upsert(cart);

                    this.cartDetailsService.delete(cartDetailUpdate);
            }
        }

        return "redirect:/client/shopping";
    }

    @PostMapping("/shopping/clear")
    public String handleClearInCartString() {
        Cart cart = this.cartService.getOneByUser(this.userService.getUserLogin());
        if (cart != null) {
            cart.setTotalPrice(0L);
            cart.getCartDetails().clear();
            this.cartService.upsert(cart);
        }

        return "redirect:/client/shopping";
    }

    @GetMapping("/shopping/handle-order/{id}")
    public String getHandleOrderPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("order", this.orderService.getOneById(id));
        model.addAttribute("page", "/handle-order");
        return "layouts/client-layout";
    }

    @PostMapping("/shopping/handle-order/{id}")
    public String handleUpdateOrder(@PathVariable("id") Long id, @ModelAttribute("order") OrderUpdateDTO order) {
        Order orderUpdate = this.orderService.getOneById(id);
        if (orderUpdate != null && order.getStatus() != null) {
            orderUpdate.setStatus(order.getStatus());
            this.orderService.upsert(orderUpdate);
        }

        return "redirect:/client/shopping";
    }

    @GetMapping("/payment")
    public String getPaymentPage(Model model) {
        User user = this.userService.getUserLogin();
        // Cart cart =
        // this.cartService.getOneByUser(this.userService.getUserLogin());

        model.addAttribute("user", user);
        // model.addAttribute("cart", cart);
        model.addAttribute("page", "/payment");
        return "layouts/client-layout";
    }

    @PostMapping("/payment/confirm-checkout")
    public String postMethodName(@RequestParam("receiver-fullname") String receiverFullname,
            @RequestParam("receiver-phone") String receiverPhone,
            @RequestParam("receiver-address") String receiverAddress, @RequestParam("pay-method") String payMethod) {
        User user = this.userService.getUserLogin();
        if (user != null) {
            Cart cart = user.getCart();

            Order newOrder = new Order();
            newOrder.setCustomer(user);
            newOrder.setPayMethod(payMethod);
            newOrder.setReceiverFullname(receiverFullname);
            newOrder.setReceiverPhone(receiverPhone);
            newOrder.setReceiverAddress(receiverAddress);
            newOrder.setTotalPrice(cart.getTotalPrice());
            this.orderService.upsert(newOrder);

            for (CartDetails cartDetails : cart.getCartDetails()) {
                OrderDetails newOrderDetails = new OrderDetails();
                newOrderDetails.setId(new OrderDetailsId(newOrder.getId(), cartDetails.getFood().getId()));
                newOrderDetails.setOrder(newOrder);
                newOrderDetails.setFood(cartDetails.getFood());
                newOrderDetails.setPrice(cartDetails.getPrice());
                newOrderDetails.setQuantity(cartDetails.getQuantity());
                this.orderDetailsService.upsert(newOrderDetails);
            }

            cart.setTotalPrice(0L);
            cart.getCartDetails().clear();
            this.cartService.upsert(cart);
        }

        return "redirect:/client/shopping";
    }

}
