package com.zm.a1.controller;

import com.zm.a1.model.ApiToken;
import com.zm.a1.model.User;
import com.zm.a1.service.ApiTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api-tokens")
public class ApiTokenController {

    @Autowired
    private ApiTokenService apiTokenService;

    @PostMapping
    public ResponseEntity<ApiToken> createToken(@RequestBody ApiToken apiToken, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        apiToken = apiTokenService.createToken(apiToken, user);
        return ResponseEntity.ok(apiToken);
    }

    @GetMapping
    public ResponseEntity<List<ApiToken>> getTokens(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(apiTokenService.getTokens(user));
    }

    @DeleteMapping
    public ResponseEntity<String> revokeToken(@RequestParam String token, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        apiTokenService.revokeToken(token, user);
        return ResponseEntity.ok("Token revoked successfully");
    }
}
