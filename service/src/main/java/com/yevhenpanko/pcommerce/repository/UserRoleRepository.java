package com.yevhenpanko.pcommerce.repository;

import com.yevhenpanko.pcommerce.entity.user.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

}
