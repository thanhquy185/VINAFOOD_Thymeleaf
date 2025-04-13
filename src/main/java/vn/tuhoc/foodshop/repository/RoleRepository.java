package vn.tuhoc.foodshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tuhoc.foodshop.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Methods
    Role findOneById(Long id);
    List<Role> findAll();
    Role save(Role user);   // Nếu tồn tại thì UPDATE, ngược lại thì INSERT INTO
    void deleteById(Long id);
}
