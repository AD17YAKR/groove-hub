//package com.groovehub.auth.util;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.Claims;
//import org.springframework.stereotype.Component;
//import java.util.Date;
//
//import static io.jsonwebtoken.Jwts.*;
//
//@Component
//public class JwtUtil {
//    private final String secretKey = "your-secret-key"; // Replace with a secure key
//    private final long expirationMs = 86400000; // 1 day
//
//    public String generateToken(String username, String role) {
//        return builder()
//                .setSubject(username)
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//    }
//
//    public Claims validateToken(String token) {
//        return parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
