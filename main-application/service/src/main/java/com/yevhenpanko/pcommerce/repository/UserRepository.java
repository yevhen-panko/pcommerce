package com.yevhenpanko.pcommerce.repository;

import com.yevhenpanko.pcommerce.entity.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query
    Optional<User> findByEmail(String email);
}
