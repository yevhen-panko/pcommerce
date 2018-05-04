package com.yevhenpanko.pcommerce.repository;

import com.yevhenpanko.pcommerce.entity.user.Permission;
import com.yevhenpanko.pcommerce.entity.user.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    @Query
    Optional<UserRole> findByName(String name);

    @Query
    List<UserRole> findAllByPermissionsIsContaining(Permission permission);
}
