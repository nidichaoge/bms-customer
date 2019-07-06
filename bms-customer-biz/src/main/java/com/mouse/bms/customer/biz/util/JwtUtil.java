package com.mouse.bms.customer.biz.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : JwtUtil
 * @date : 2019/4/6 14:06
 * @description :
 */
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    private static final Long EXPIRE_TIME = 10 * 60 * 1000L;

    /**
     * 生成token
     */
    public static String generateToken(String username, String password) {
        Algorithm algorithm = Algorithm.HMAC256(password);
        Map<String, Object> map = new HashMap<>(2);
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
            //头部信息
            .withHeader(map)
            //过期时间
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
            //签名由谁生成，服务器
            .withIssuer("MOUSE")
            //生成签名的时间
            .withIssuedAt(new Date())
            //谁接受
            .withAudience("h5")
            //签名主题
            .withSubject("Subject")
            //定义在什么时间之前，该jwt都是不可用的
//            .withNotBefore()
            .withClaim("username", username)
            //签名的signature
            .sign(algorithm);
    }

    /**
     * 从token中获得username
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            LOGGER.warn("JwtUtil | getUserName, token:{}.", token);
            return null;
        }
    }

    /**
     * 验证token
     */
    public static boolean verify(String token, String username, String password) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).withClaim("username", username).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
