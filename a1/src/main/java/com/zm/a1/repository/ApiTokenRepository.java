package com.zm.a1.repository;

import com.zm.a1.model.ApiToken;
import com.zm.a1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiTokenRepository extends JpaRepository<ApiToken, Long> {
    List<ApiToken> findByUserAndRevokedFalse(User user);

    ApiToken findByTokenAndUser(String token, User user);
}
