package vn.tuhoc.foodshop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.domain.dto.RegisterDTO;
import vn.tuhoc.foodshop.service.RoleService;
import vn.tuhoc.foodshop.service.UserService;


@Controller
public class PublicController {
    // Properties
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    // Constructors
    public PublicController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    // Methods
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("page", "login");
        return "layouts/public-layout";
    }
    
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("page", "register");
        return "layouts/public-layout";
    }

    @PostMapping("/register")
    public String handleRegisterUser(@ModelAttribute RegisterDTO user) throws Exception {
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Xác nhận mật khẩu sai !");
        }
        if(this.userService.getOneUserByUsername(user.getUsername()) != null) {
            throw new Exception("Tên người dùng đã tồn tại !");
        }

        User userRegister = new User();
        userRegister.setUsername(user.getUsername());
        userRegister.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRegister.setStatus(true);
        userRegister.setRole(this.roleService.getOneById(new Long(3)));
        userRegister.setFullname("Chưa cập nhật");
        userRegister.setPhone("0000000000");
        userRegister.setAddress("Chưa cập nhật");
        this.userService.upsertUser(userRegister);

        return "redirect:/login";
    }

    @GetMapping("/403")
    public String get403Page(Model model) {
        model.addAttribute("page", "403");
        return "layouts/public-layout";
    }
}
