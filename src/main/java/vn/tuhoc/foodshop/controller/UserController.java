package vn.tuhoc.foodshop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import vn.tuhoc.foodshop.domain.Role;
import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.domain.User_;
import vn.tuhoc.foodshop.domain.criteria.UserCriteria;
import vn.tuhoc.foodshop.domain.dto.UserLockDTO;
import vn.tuhoc.foodshop.domain.dto.UserUpdateDTO;
import vn.tuhoc.foodshop.service.RoleService;
import vn.tuhoc.foodshop.service.UploadService;
import vn.tuhoc.foodshop.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    // Properties
    private final UserService userService;
    private final RoleService roleService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    // Contructors
    public UserController(UserService userService, RoleService roleService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    // Methods
    @GetMapping("")
    public String getUsersPage(Model model, UserCriteria userCriteria, HttpServletRequest request) {
        // Đảm bảo page mặc định là 1 dù người dùng có tự nhập
        int page = 1;
        try {
            if (userCriteria.getPage().isPresent()) {
                page = Integer.parseInt(userCriteria.getPage().get());
            } else {

            }
        } catch (Exception e) {

        }
        // Tuỳ theo tiêu chí sắp xếp mà trả về dữ liệu (mặc định là ID tăng dần)
        Pageable pageable = PageRequest.of(page - 1, 10);
        ;
        if (userCriteria.getSort() != null && userCriteria.getSort().isPresent()) {
            String sort = userCriteria.getSort().get();
            if (sort.equals("ID tăng dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(User_.ID).ascending());
            } else if (sort.equals("ID giảm dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(User_.ID).descending());
            } else if (sort.equals("Họ và tên tăng dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(User_.FULLNAME).ascending());
            } else if (sort.equals("Họ và tên giảm dần")) {
                pageable = PageRequest.of(page - 1, 10, Sort.by(User_.FULLNAME).descending());
            }
        }
        // Cập nhật dữ liệu và chức năng phân trang
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.isBlank()) {
            queryString = queryString.replace("page=" + page, "");
        }
        Page<User> pageUser = this.userService.getAllUser(pageable, userCriteria);
        List<User> listUser = pageUser.getContent();
        List<Role> listRole = this.roleService.getAll();

        // Thêm thuộc tính cần thiết
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageUser.getTotalPages());
        model.addAttribute("queryString", queryString);
        model.addAttribute("users", listUser);
        model.addAttribute("roles", listRole);
        model.addAttribute("page", "/user/list-user");

        return "layouts/admin-layout";
    }

    @GetMapping("/detail/{id}")
    public String getDetailUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("user", this.userService.getOneUserById(id));
        model.addAttribute("roles", this.roleService.getAll());
        model.addAttribute("page", "/user/detail-user");
        return "layouts/admin-layout";
    }

    @GetMapping("/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("user", new User());
        model.addAttribute("roles", this.roleService.getAll());
        model.addAttribute("page", "/user/create-user");

        return "layouts/admin-layout";
    }

    @PostMapping("/create")
    public String handleCreateUser(@ModelAttribute("user") @Valid User user,
            @RequestParam("file-image") MultipartFile fileImage) {
        // Lấy ra tổng số lượng món ăn hiện có tương ứng với mã món ăn mới
        List<User> listUser = this.userService.getAllUser();

        // Cập nhật file ảnh vào source code và lấy ra tên file để lưu vào csdl
        String image = null;
        if (fileImage != null && !fileImage.isEmpty()) {
            image = this.uploadService.uploadImageFiles(fileImage, "users", String.valueOf(listUser.size() + 1));
        }
        user.setImage(image);

        // Mã hoá mật khẩu
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        // Thêm vào csdl
        this.userService.upsertUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("user", this.userService.getOneUserById(id));
        model.addAttribute("roles", this.roleService.getAll());
        model.addAttribute("page", "/user/update-user");
        return "layouts/admin-layout";
    }

    @PostMapping("/update/{id}")
    public String handleUpdateUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserUpdateDTO user,
            @RequestParam("file-image") MultipartFile fileImage) {
        // Cập nhật file ảnh vào source code và lấy ra tên file để lưu vào csdl
        String image = null;
        if (fileImage != null && !fileImage.isEmpty()) {
            image = this.uploadService.uploadImageFiles(fileImage, "users", String.valueOf(id));
        }
        user.setImage(image);

        // Mã hoá mật khẩu
        // String hashPassword = this.passwordEncoder.encode(user.getPassword());
        // user.setPassword(hashPassword);

        // Cập nhật dữ liệu đã thay đổi
        User userUpdate = this.userService.getOneUserById(id);
        if(userUpdate != null) {
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

        return "redirect:/admin/users";
    }

    @GetMapping("/lock/{id}")
    public String getLockUserPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("userLogin", this.userService.getUserLogin());
        model.addAttribute("user", this.userService.getOneUserById(id));
        model.addAttribute("page", "/user/lock-user");
        return "layouts/admin-layout";
    }

    @PostMapping("/lock/{id}")
    public String handleLockUser(@PathVariable("id") Long id, @ModelAttribute("user") @Valid UserLockDTO user) {
        User userLock = this.userService.getOneUserById(id);
        if(userLock != null) {
            userLock.setStatus(!user.getStatus());
            userLock.setTimeUpdate(LocalDateTime.now());
            this.userService.upsertUser(userLock);
        }
        
        return "redirect:/admin/users";
    }
}
