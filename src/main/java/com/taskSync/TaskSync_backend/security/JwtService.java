package com.taskSync.TaskSync_backend.security;

import com.taskSync.TaskSync_backend.entity.Jwt;
import com.taskSync.TaskSync_backend.entity.User;
import com.taskSync.TaskSync_backend.repository.JwtRepository;
import com.taskSync.TaskSync_backend.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    public static final String BEARER = "bearer";
    private UserService userService;
    private JwtRepository jwtRepository;
    private final String ENCRYPTION_KEY = "fdee140d0a07193a356763552ad2a03cd75e2ebe55da60cca6d9c738df3ba993";

    public JwtService(UserService userService, JwtRepository jwtRepository) {
        this.userService = userService;
        this.jwtRepository = jwtRepository;
    }

    public Map<String, String> generate(String username){
        UserDetails user = this.userService.loadUserByUsername(username);
        Map<String, String> jwtMap = this.generateJwt(user);
        Jwt jwt = Jwt.builder().value(jwtMap.get(BEARER)).disable(false).expire(false).user((User) user).build();
        this.jwtRepository.save(jwt);
        return jwtMap;
    }

    private Map<String, String> generateJwt(UserDetails user) {
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + 30 * 60 * 1000;

        String bearer = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expirationTime))
                .claim("username", user.getUsername())
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return Map.of("bearer", bearer);
    }
    private Key getKey(){
        byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);

        return Keys.hmacShaKeyFor(decoder);
    }

    public String loadUserByUsername(String token) {
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    private <T> T getClaim(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
