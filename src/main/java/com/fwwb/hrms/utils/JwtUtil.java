package com.fwwb.hrms.utils;


import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 黄天赐
 * @Date: Created in 10:41 2021/2/21
 */
@Component
public class JwtUtil {
    /**
     * JWT验证过期时间 从配置文件中获取
     */
    private static long expire_time;

    @Value("${shiro-redis.cache-manager.expire}")
    public void setExpire_time(long time) {
        expire_time = time * 1000;
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT校验器
            Algorithm algorithm = Algorithm.HMAC256(secret);//加密
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //校验TOKEN
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiresAt = jwt.getExpiresAt();
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的toke
     */
    public static String createToken(String username, String secret) {
        try {
            System.out.println(expire_time);
            Date date = new Date(System.currentTimeMillis() + expire_time);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
