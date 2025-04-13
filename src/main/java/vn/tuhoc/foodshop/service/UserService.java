package vn.tuhoc.foodshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.User;
import vn.tuhoc.foodshop.domain.criteria.UserCriteria;
import vn.tuhoc.foodshop.repository.UserRepository;
import vn.tuhoc.foodshop.service.specfication.UserSpecification;

@Service
public class UserService {
    // Properties
    private final UserRepository userRepository;

    // Constructors
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Methods
    public User getOneUserById(Long id) {
        return this.userRepository.findOneById(id);
    }

    public User getOneUserByUsername(String username) {
        return this.userRepository.findOneByUsername(username);
    }

    public User getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userLogin = new User();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            userLogin = getOneUserByUsername(userDetails.getUsername());
        }

        return userLogin;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public Page<User> getAllUser(Pageable page, UserCriteria userCriteria) {
        //
        if (userCriteria.getId() == null && userCriteria.getFullname() == null && userCriteria.getPhone() == null
                && userCriteria.getRole() == null
                && userCriteria.getStatus() == null) {
            return this.userRepository.findAll(page);
        }

        //
        Specification<User> combinedSpec = Specification.where(null);
        if (userCriteria.getId() != null && userCriteria.getId().isPresent()) {
            if (userCriteria.getId().get().matches("\\d+")) {
                Specification<User> currentSpec = UserSpecification.idEqual(userCriteria.getId().get());
                combinedSpec = combinedSpec.or(currentSpec);
            }
        }
        if (userCriteria.getFullname() != null && userCriteria.getFullname().isPresent()) {
            Specification<User> currentSpec = UserSpecification.fullnameLike(userCriteria.getFullname().get());
            combinedSpec = combinedSpec.or(currentSpec);
        }
        if (userCriteria.getPhone() != null && userCriteria.getPhone().isPresent()) {
            if (userCriteria.getId().get().matches("\\d+")) {
                Specification<User> currentSpec = UserSpecification.phoneEqual(userCriteria.getPhone().get());
                combinedSpec = combinedSpec.or(currentSpec);
            }
        }
        if (userCriteria.getRole() != null && userCriteria.getRole().isPresent()) {
            Specification<User> currentSpec = UserSpecification.roleEqual(userCriteria.getRole().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }
        if (userCriteria.getStatus() != null && userCriteria.getStatus().isPresent()) {
            Specification<User> currentSpec = UserSpecification
                    .statusEqual(userCriteria.getStatus().get().equals("Hoạt động") ? true : false);
            combinedSpec = combinedSpec.and(currentSpec);
        }

        return this.userRepository.findAll(combinedSpec, page);
    }

    public User upsertUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    public void lockUser(User userLocked) {
        this.userRepository.save(userLocked);
    }
}
