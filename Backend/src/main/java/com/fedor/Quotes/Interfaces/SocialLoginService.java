package com.fedor.Quotes.Interfaces;

import org.springframework.http.ResponseEntity;

public interface SocialLoginService {
    public ResponseEntity<?> handleLogin(String accessToken);
    public String getProvider();
}
