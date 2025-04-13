package vn.tuhoc.foodshop.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    // Properties
    private final UserService userService;

    // Constructors
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    // Methods
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        vn.tuhoc.foodshop.domain.User user = this.userService.getOneUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Người dùng không tồn tại !");
        }

        String roleName = "ROLE_ADMIN";
        if(user.getRole().getName().equals("Khách hàng")) {
            roleName = "ROLE_USER";
        }

        return new User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(roleName)));

    }
}
