package security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import payload.CustomUser;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${BlogApp.app.secret}")
    private String APP_SECRET;

    @Value("${BlogApp.expires.in}")
    private Long EXPIRES_IN;

    public String generateJwtToken(Authentication auth){
        CustomUser userDetails = (CustomUser) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET)
                .compact();
    }

    String getEmailFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);

            return !isTokenExpired(token);

        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }

    }

    private boolean isTokenExpired(String token) {

        Date expiration = Jwts.parser()
                .setSigningKey(APP_SECRET)
                .parseClaimsJws(token).
                getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
