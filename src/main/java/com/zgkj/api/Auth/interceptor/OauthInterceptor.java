package com.zgkj.api.Auth.interceptor;

import com.util.JwtUtil;
import com.zgkj.api.Auth.exception.ApiException;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OauthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String authorization = request.getHeader("authorization");
        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.substring(7);
            Claims claims = JwtUtil.verifyToken(token);
            if((Long)claims.get("exp")>System.currentTimeMillis()){
                request.setAttribute("clientId", claims.get("clientId"));
            }else {
                throw new ApiException("4011","Token过期请重新获取");
            }
        } else {
            throw new ApiException("401","缺少Token");
        }
        return true;
    }
}
