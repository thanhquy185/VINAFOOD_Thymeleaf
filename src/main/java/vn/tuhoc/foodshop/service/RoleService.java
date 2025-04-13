package vn.tuhoc.foodshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.tuhoc.foodshop.domain.Role;
import vn.tuhoc.foodshop.repository.RoleRepository;

@Service
public class RoleService {
    // Properties
    private final RoleRepository roleRepository;

    // Constructors
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Methods
    public Role getOneById(Long id) {
        return this.roleRepository.findOneById(id);
    }

    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }

    public void upsert(Role Role) {
        this.roleRepository.save(Role);
    }

    public void deleteById(Long id) {
        this.roleRepository.deleteById(id);
    }
}
