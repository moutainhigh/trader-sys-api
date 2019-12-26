package com.zgkj.api.trader.controller;

import com.zgkj.util.JwtUtil;
import com.zgkj.api.Auth.exception.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @GetMapping("/oauth/token")
    public String token(String clientId, String clientSecret) {
        if ("sds".equals(clientId) && "zgkj66886".equals(clientSecret)) {
            Claims claims = Jwts.claims();
            claims.put("clientId", clientId);
            claims.put("exp", System.currentTimeMillis() + 7200000);
            JwtUtil.createToken(claims);
            return JwtUtil.createToken(claims);
        }
        throw new ApiException("401", "无效的Client组合");
    }


}
