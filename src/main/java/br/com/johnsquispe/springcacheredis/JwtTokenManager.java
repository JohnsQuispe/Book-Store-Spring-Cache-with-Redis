package br.com.johnsquispe.springcacheredis;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenManager {

    private final String secretKey;
    private final long expirationInMillis;
    private final JwtParser jwtParser;

    public JwtTokenManager(@Value("${bookmanager.jwt.secret-key}") String secretKey, @Value("${bookmanager.jwt.expiration-time-in-millis}") long expirationInMillis) {
        this.secretKey = secretKey;
        this.expirationInMillis = expirationInMillis;
        this.jwtParser = Jwts.parser();
    }

    public String generateToken (UserDetailsImpl userDetails) {

        final Date expiration = new Date(new Date().getTime() + this.expirationInMillis);

        return Jwts.builder()
                .setIssuer("Book Manager")
                .setSubject(userDetails.getUsername())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();

    }

    public boolean isValid (String jwtToken) {

        try {

            if (StringUtils.isBlank(jwtToken)) {
                return false;
            }

            this.jwtParser.setSigningKey(this.secretKey)
                                .parseClaimsJws(jwtToken);

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }

    public UserDetailsImpl toUserDetails (String jwtToken) {

        final Claims claims = this.jwtParser.setSigningKey(this.secretKey)
                                                .parseClaimsJws(jwtToken)
                                                .getBody();

        return new UserDetailsImpl(
                null,
                claims.getSubject(),
                Collections.emptyList()
        );

    }

}
