package com.dizhejiang.teachin.common;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.enums.ResponseEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;

/**
 * Json Web Token 工具类
 * 官方提供了7个Payload字段,提供使用
 * iss (issuer)：签发人
 * exp (expiration time)：过期时间
 * sub (subject)：主题
 * aud (audience)：受众
 * nbf (Not Before)：生效时间
 * iat (Issued At)：签发时间
 * jti (JWT ID)：编号
 * @Auther: Tt(yehuawei)
 * @Date: 2019/4/10 09:45
 */
public class JWTUtil {

    /**
     * 加密秘钥
     */
    private static final String JWT_SECRET = "UCI*SNN";
    /**
     * 签发人 灏宸科技
     */
    private static final String ISS_USER = "wjs";
    /**
     * 过期时间 20小时
     */
    private static final Long EXPIRATION_TIME = 20L * 60 * 60 * 1000;

    /**
     * 加密
     */
    private static Algorithm HMAC256_ALGORITHM = HMAC256(JWT_SECRET);

//    static {
//        try {
//            HMAC256_ALGORITHM = HMAC256(JWT_SECRET);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }


    /**
     * 获取JWT(JSON WEB TOKEN)
     * @param claims JWT Payload部分的参数，支持自定义的map，明文显示注意信息安全性，或使用密文
     * @return
     */
    public static String getJWT(Map<String,String> claims){
        JWTCreator.Builder builder = JWT.create().withIssuer(ISS_USER)
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME));
            claims.forEach((k,v) -> builder.withClaim(k,v));
//        for (Map.Entry<String,String> entry : claims.entrySet()){
//            builder.withClaim(entry.getKey(),entry.getValue());
//        }
        return builder.sign(HMAC256_ALGORITHM);
    }

    /**
     * 获取JWT中的所有声明(就是之前自定义放入JWT中的内容)
     * @param token
     * @return
     */
    public static Map<String,String> getJWTClaim(String token){
        try {
            JWTVerifier verifier = JWT.require(HMAC256_ALGORITHM)
                    .withIssuer(ISS_USER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            Map<String,String> resultMap = new HashMap<>();
            jwt.getClaims().forEach((k,v)->resultMap.put(k,v.asString()));
            return resultMap;
            //Exception in thread "main" com.auth0.jwt.exceptions.InvalidClaimException: The Token has expired on Tue Apr 09 05:07:46 CST 2019./The Claim 'iss' value doesn't match the required one.
        }catch (InvalidClaimException e){

            e.printStackTrace();
            throw new ResponseException(ResponseEnum.JWT_INVALID_CLAIM);

            //Exception in thread "main" com.auth0.jwt.exceptions.SignatureVerificationException: The Token's Signature resulted invalid when verified using the Algorithm: HmacSHA256
        }catch (SignatureVerificationException e1){

            e1.printStackTrace();
            throw new ResponseException(ResponseEnum.JWT_INVALID_CLAIM);
        }
    }




    public static void main(String[] args) {
        Map<String,String> claims = new HashMap<String, String>();
        claims.put("id","1");
        claims.put("name","admin");
        String token = getJWT(claims);
//        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJoY2tqMSIsIm5hbWUiOiJhZG1pbiIsImlkIjoiMSIsImV4cCI6MTU1NDc1NzY2Nn0.pxMnz30m_AFuLX9tTkup82NIcM9Tg1wTjj7Uvp_SUzs";
        System.out.println("token: "+token);
        System.out.println("getJWTClaim: "+JSON.toJSONString(getJWTClaim(token)));
    }
}
