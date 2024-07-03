package org.acme;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtGenerator {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateJwt(String username) {
        byte[] secretKeyBytes = SECRET_KEY.getEncoded();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 ora
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes) // Modifica qui
                .compact();
    }

    public static SecretKey getSecretKey() {
        return SECRET_KEY;
    }
}
