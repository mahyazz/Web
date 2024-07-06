package com.zm.a1.service;

import com.zm.a1.model.ApiToken;
import com.zm.a1.model.User;
import com.zm.a1.repository.ApiTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ApiTokenService {

    @Autowired
    private ApiTokenRepository apiTokenRepository;

    public ApiToken createToken(ApiToken apiToken, User user) {
        apiToken.setToken(UUID.randomUUID().toString());
        apiToken.setUser(user);
        apiToken.setRevoked(false);
        return apiTokenRepository.save(apiToken);
    }

    public List<ApiToken> getTokens(User user) {
        return apiTokenRepository.findByUserAndRevokedFalse(user);
    }

    public void revokeToken(String token, User user) {
        ApiToken apiToken = apiTokenRepository.findByTokenAndUser(token, user);
        if (apiToken != null) {
            apiToken.setRevoked(true);
            apiTokenRepository.save(apiToken);
        }
    }
}
