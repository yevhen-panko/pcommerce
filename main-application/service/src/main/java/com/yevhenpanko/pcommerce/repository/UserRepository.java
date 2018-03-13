package com.yevhenpanko.pcommerce.repository;

import com.yevhenpanko.pcommerce.entity.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
