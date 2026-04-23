package org.example.blackholetourismagencybook.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "my-super-secert-and-long-key-XD-LOL-can-you-find-me-son-of-a-beach";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    //eyJhbGci(Header (Red):Specifies the algorithm (e.g., HS256) and type (JWT).).
    // xxxxxx.(Payload (Purple): Contains claims, such as user data (e.g., username, ID) and expiration time.)
    // yyyyyy(Signature (Blue): Encrypts the header and payload to ensure data integrity.
    public String generateToken(String username, String role){
        long now = System.currentTimeMillis();
        long expiry = 1000 * 60 * 60 * 10;

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expiry))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return parseClaims(token).getSubject();//get name from payload.

    }

    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }


    public Claims parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key) //according to the jwt header(the algorithm inside it), calculate if the sign is the same to key at the top
                .build()
                //This method returns payload(name,time,role) in a claims structure.
                .parseClaimsJws(token)
                .getBody();
    }

}
