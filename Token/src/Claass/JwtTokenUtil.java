package Claass;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import com.google.gson.Gson;

public class JwtTokenUtil {
    private static final String SECRET_KEY = "mySuperSecretKey123456789012345111167890"; // 최소 32바이트 필요
    private static final long EXPIRATION_TIME = 1000 * 30 * 1; // 30초

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ✅ 1. JWT 토큰 생성
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)  // 사용자 이름 설정
                .setIssuedAt(new Date())  // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 (HMAC-SHA256)
                .compact();
    }

    // ✅ 2. JWT 토큰 검증 및 사용자 정보 추출
    public static String validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // 토큰에서 사용자 정보(Subject) 추출
        } catch (JwtException e) {
            return "Invalid token"; // 토큰이 유효하지 않음
        } catch (Exception e) {
            return "error token"; // 토큰이 유효하지 않음
        }
    }
    
    public static void main(String[] args) {
        String token = generateToken("윤석준윤석준윤석준윤석준윤석준윤석준윤석준윤석준윤석준");
        System.out.println("Generated Token: " + token);

        String user = validateToken(token);
//        String user = validateToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIDsnKTshJ3spIAiLCJpYXQiOjE3NDAxMjA0MzksImV4cCI6MTc0MDEyMDQ2OX0.-bGD_96oZ2o5gE1ObJToA2D-aQ5wvBL_RdTaQYg1JH0");
        System.out.println("Decoded User: " + user);
    }
}
