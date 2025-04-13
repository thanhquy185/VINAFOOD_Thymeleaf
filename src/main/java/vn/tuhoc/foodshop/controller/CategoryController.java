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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import vn.tuhoc.foodshop.domain.Category;
import vn.tuhoc.foodshop.domain.Category_;
import vn.tuhoc.foodshop.domain.criteria.CategoryCriteria;
import vn.tuhoc.foodshop.domain.dto.CategoryLockDTO;
import vn.tuhoc.foodshop.domain.dto.CategoryUpdateDTO;
import vn.tuhoc.foodshop.service.CategoryService;
import vn.tuhoc.foodshop.service.UserService;


@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    // Properties
    private final UserService userService;
    private final CategoryService categoryService;

    // Constructors
    public CategoryController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    // Methods
    @GetMapping("")
    public String getListCategoryPage(Model model, CategoryCriteria categoryCriteria, HttpServletRequest request) {
        // Đảm bảo page mặc định là 1 dù người dùng có tự nhập
        int page = 1;
        try {
            if (categoryCriteria.getPage().isPresent()) {
                page = Integer.parseInt(categoryCriteria.getPage().get());
            } else {

            }
        } catch (Exception e) {

        }
        //
        Pageable pageable = PageRequest.of(page - 1, 10);
        if (categoryCriteria.getSort() != null && categoryCriteria.getSort().isPresent()) {
            String sort = categoryCriteria.getSort().get();
            if (sort.equals("ID tăng dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(Category_.ID).ascending());
            } else if (sort.equals("ID giảm dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(Category_.ID).descending());
            } else if (sort.equals("Tên loại tăng dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(Category_.NAME).ascending());
            } else if (sort.equals("Tên loại giảm dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(Category_.NAME).descending());
            }
        }
        //
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            queryString = queryString.replace("page=" + page, "");
        }
        Page<Category> pageCategory = this.categoryService.getAll(pageable, categoryCriteria);
        List<Category> listCategory = pageCategory.getContent();

        //
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageCategory.getTotalPages());
        model.addAttribute("categories", listCategory);
        model.addAttribute("queryString", queryString);
        model.addAttribute("page", "category/list-category");

        return "layouts/admin-layout";
    }
    
    @GetMapping("/create")
    public String getCreateCategoryPage(Model model) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("category", new Category());
        model.addAttribute("page", "category/create-category");

        return "layouts/admin-layout";
    }

    @PostMapping("/create")
    public String handleCreateCategory(@ModelAttribute("category") @Valid Category category) {
        this.categoryService.upsert(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/update/{id}")
    public String getUpdateCategoryPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("category", this.categoryService.getOneById(id));
        model.addAttribute("page", "category/update-category");

        return "layouts/admin-layout";
    }

    @PostMapping("/update/{id}")
    public String handleUpdateCategory(@PathVariable("id") Long id, @ModelAttribute("category") @Valid CategoryUpdateDTO category) {
        Category categoryUpdate = this.categoryService.getOneById(id);
        if(categoryUpdate != null) {
            categoryUpdate.setName(category.getName());
            categoryUpdate.setTimeUpdate(LocalDateTime.now());
        }
        this.categoryService.upsert(categoryUpdate);

        return "redirect:/admin/categories";
    }
    
    @GetMapping("/lock/{id}")
    public String getLockCategoryPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("category", this.categoryService.getOneById(id));
        model.addAttribute("page", "category/lock-category");

        return "layouts/admin-layout";
    }

    @PostMapping("/lock/{id}")
    public String handleLockCategory(@PathVariable("id") Long id, @ModelAttribute("category") @Valid CategoryLockDTO category) {
        Category categoryLock = this.categoryService.getOneById(id);
        if(categoryLock != null) {
            categoryLock.setStatus(!category.getStatus());
            categoryLock.setTimeUpdate(LocalDateTime.now());
        }
        this.categoryService.upsert(categoryLock);

        return "redirect:/admin/categories";
    }
}
