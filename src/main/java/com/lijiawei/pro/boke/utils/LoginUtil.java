package com.lijiawei.pro.boke.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  登录相关工具类
 */
@Component
public class LoginUtil {

    // JWT 加密用的密钥
    private static String secret = "cypher";

    @Value("${jwt.secret:cypher}")
    public void setSecret(String secret) {
        LoginUtil.secret = secret;
    }

    public static String getJWTToken(Long id) {
        String token = JWT.create().withClaim("userId", id)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    public static Long resolveTokenToUserId(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT verify = null;
        try {
            verify = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("userId",verify.getClaim("userId"));
//        map.put("issuedAt",verify.getIssuedAt());
//        map.put("expireAt",verify.getExpiresAt());
        return verify.getClaim("userId").asLong();
    }
}
