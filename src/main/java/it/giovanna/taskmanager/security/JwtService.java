package it.giovanna.taskmanager.security;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final  Key key;
    private final long expirationMs;;

    public JwtService(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-minutes}") long expMinutes
    ){this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs=expMinutes*60_000;}

    public String generateToken(String username){
        Date now=new Date();
        Date exp=new Date(now.getTime()+expirationMs);
               return Jwts.builder()
                       .setSubject(username)
                       .setIssuedAt(now)
                       .setExpiration(exp)
                       .signWith(key, SignatureAlgorithm.HS256)
                       .compact();
    }

    public String extractUsername(String token){
        return parseClaims(token).getBody().getSubject();
    }
     public boolean isValid(String token){
        try{
            parseClaims(token);
            return true;
        }catch(JwtException | IllegalArgumentException e){
            return false;
         }
     }
     private Jws<Claims>
    parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
     }
}
