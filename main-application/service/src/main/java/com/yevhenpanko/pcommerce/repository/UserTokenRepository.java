package com.yevhenpanko.pcommerce.repository;

import com.yevhenpanko.pcommerce.entity.user.UserToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTokenRepository extends CrudRepository<UserToken, Long> {

    @Query
    Optional<UserToken> findBySeries(String series);

    @Query
    void deleteByEmail(String email);
}
