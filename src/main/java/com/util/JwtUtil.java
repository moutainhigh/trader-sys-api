package com.util;

import com.zgkj.api.Auth.exception.ApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;

public class JwtUtil {
    private static final String SECRET="A1B2C3D4E5F6G7H8I9J1K2L3M4N5O6P7Q8R9S1T2U3V4W5X6Y7Z";

    public static String createToken(Claims claims){
        return Jwts.builder().setClaims(claims).signWith(generalKey()).compact();
    }
    public static Claims verifyToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException | IllegalArgumentException | SignatureException | MalformedJwtException | UnsupportedJwtException e) {
            throw new ApiException("401","无效的Token");
        }
        return claims;
    }
    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(SECRET);
        return Keys.hmacShaKeyFor(encodedKey);
    }
}
