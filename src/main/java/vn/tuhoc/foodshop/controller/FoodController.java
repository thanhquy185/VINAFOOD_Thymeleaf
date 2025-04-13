package vn.tuhoc.foodshop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import vn.tuhoc.foodshop.domain.Category;
import vn.tuhoc.foodshop.domain.Food;
import vn.tuhoc.foodshop.domain.Food_;
import vn.tuhoc.foodshop.domain.criteria.FoodCriteria;
import vn.tuhoc.foodshop.domain.dto.FoodLockDTO;
import vn.tuhoc.foodshop.domain.dto.FoodUpdateDTO;
import vn.tuhoc.foodshop.service.CategoryService;
import vn.tuhoc.foodshop.service.FoodService;
import vn.tuhoc.foodshop.service.UploadService;
import vn.tuhoc.foodshop.service.UserService;

@Controller
@RequestMapping("/admin/foods")
public class FoodController {
    // Properties
    private final UserService userService;
    private final FoodService foodService;
    private final CategoryService categoryService;
    private final UploadService uploadService;

    // Constructors
    public FoodController(UserService userService, FoodService foodService, CategoryService categoryService, UploadService uploadService) {
        this.userService = userService;
        this.foodService = foodService;
        this.categoryService = categoryService;
        this.uploadService = uploadService;
    }

    @GetMapping("")
    public String getListFoodPage(Model model, FoodCriteria foodCriteria, HttpServletRequest request) {
        // Đảm bảo page mặc định là 1 dù người dùng có tự nhập
        int page = 1;
        try {
            if (foodCriteria.getPage().isPresent()) {
                page = Integer.parseInt(foodCriteria.getPage().get());
            } else {

            }
        } catch (Exception e) {

        }
        //
        Pageable pageable = PageRequest.of(page - 1, 5);
        if (foodCriteria.getSort() != null && foodCriteria.getSort().isPresent()) {
            String sort = foodCriteria.getSort().get();
            if (sort.equals("ID tăng dần")) {
                pageable = PageRequest.of(page - 1, 5, Sort.by(Food_.ID).ascending());
            } else if (sort.equals("ID giảm dần")) {
                pageable = PageRequest.of(page - 1, 5, Sort.by(Food_.ID).descending());
            } else if (sort.equals("Tên món ăn tăng dần")) {
                pageable = PageRequest.of(page - 1, 5, Sort.by(Food_.NAME).ascending());
            } else if (sort.equals("Tên món ăn giảm dần")) {
                pageable = PageRequest.of(page - 1, 5, Sort.by(Food_.NAME).descending());
            } else if (sort.equals("Tồn kho tăng dần")) {
                pageable = PageRequest.of(page - 1, 5, Sort.by(Food_.INVENTORY).ascending());
            } else if (sort.equals("Tồn kho giảm dần")) {
                pageable = PageRequest.of(page - 1, 5, Sort.by(Food_.INVENTORY).descending());
            }
        }
        //
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            queryString = queryString.replace("page=" + page, "");
        }
        Page<Food> pageFood = this.foodService.getAll(pageable, foodCriteria);
        List<Food> listFood = pageFood.getContent();
        List<Category> listCategory = this.categoryService.getAll();

        //
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageFood.getTotalPages());
        model.addAttribute("foods", listFood);
        model.addAttribute("categories", listCategory);
        model.addAttribute("queryString", queryString);
        model.addAttribute("page", "/food/list-food");

        return "layouts/admin-layout";
    }

    @GetMapping("/detail/{id}")
    public String getDetailFoodPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("food", this.foodService.getOneById(id));
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("page", "/food/detail-food");

        return "layouts/admin-layout";
    }

    @GetMapping("/create")
    public String getCreateFoodPage(Model model) {
        Food foodTemp = new Food();
        foodTemp.setInventory(new Long(0));
        List<Category> listCategory = this.categoryService.getAll();

        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("food", foodTemp);
        model.addAttribute("categories", listCategory);
        model.addAttribute("page", "/food/create-food");

        return "layouts/admin-layout";
    }

    @PostMapping("/create")
    public String handleCreateFood(@ModelAttribute("food") @Valid Food food,
            @RequestParam("file-image") MultipartFile fileImage) {
        // Lấy ra tổng số lượng món ăn hiện có tương ứng với mã món ăn mới
        List<Food> listFood = this.foodService.getAll();

        // Cập nhật file ảnh vào source code và lấy ra tên file để lưu vào csdl
        String image = null;
        if (fileImage != null && !fileImage.isEmpty()) {
            image = this.uploadService.uploadImageFiles(fileImage, "foods", String.valueOf(listFood.size() + 1));
        }
        food.setImage(image);

        // Thêm vào csdl
        this.foodService.upsert(food);

        return "redirect:/admin/foods";
    }

    @GetMapping("/update/{id}")
    public String getUpdateFoodPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("food", this.foodService.getOneById(id));
        model.addAttribute("categories", this.categoryService.getAll());
        model.addAttribute("page", "/food/update-food");
        
        return "layouts/admin-layout";
    }

    @PostMapping("/update/{id}")
    public String handleUpdateFood(@PathVariable("id") Long id, @ModelAttribute("food") @Valid FoodUpdateDTO food,
            @RequestParam("file-image") MultipartFile fileImage) {
        // Cập nhật file ảnh vào source code và lấy ra tên file để lưu vào csdl
        String image = null;
        if (fileImage != null && !fileImage.isEmpty()) {
            image = this.uploadService.uploadImageFiles(fileImage, "foods", String.valueOf(id));
        }
        food.setImage(image);

        // Gán dữ liệu cần cập nhật
        Food foodUpdate = this.foodService.getOneById(id);
        if (foodUpdate != null) {
            if (foodUpdate.getImage() == null || food.getImage() != null) {
                foodUpdate.setImage(food.getImage());
            }
            foodUpdate.setName(food.getName());
            foodUpdate.setCategory(food.getCategory());
            foodUpdate.setPrice(food.getPrice());
            foodUpdate.setDescription(food.getDescription());
            foodUpdate.setInventory(food.getInventory());
            foodUpdate.setTimeUpdate(LocalDateTime.now());
            this.foodService.upsert(foodUpdate);
        }

        return "redirect:/admin/foods";
    }

    @GetMapping("/lock/{id}")
    public String getLockFoodPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("food", this.foodService.getOneById(id));
        model.addAttribute("page", "/food/lock-food");

        return "layouts/admin-layout";
    }

    @PostMapping("/lock/{id}")
    public String handleLockFood(@PathVariable("id") Long id, @ModelAttribute("food") @Valid FoodLockDTO food) {
        Food foodLock = this.foodService.getOneById(id);
        if(foodLock != null) {
            foodLock.setStatus(!food.getStatus());
            foodLock.setTimeUpdate(LocalDateTime.now());
            this.foodService.upsert(foodLock);
        }
        
        return "redirect:/admin/foods";
    }
}
