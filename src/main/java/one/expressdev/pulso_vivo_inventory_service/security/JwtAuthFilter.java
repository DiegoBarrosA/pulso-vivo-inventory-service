package one.expressdev.pulso_vivo_inventory_service.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.client.RestTemplate;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final String secretKey = "mySecretKey"; // Debe coincidir con el usado en el auth-service

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

                String username = claims.getSubject();

                if (
                    username != null &&
                    SecurityContextHolder.getContext().getAuthentication() ==
                    null
                ) {
                    // Extract roles from JWT claims
                    List<SimpleGrantedAuthority> authorities =
                        new ArrayList<>();

                    // Option 1: If roles are stored as a list in the token
                    @SuppressWarnings("unchecked")
                    List<String> roles = (List<String>) claims.get("roles");
                    if (roles != null) {
                        for (String role : roles) {
                            authorities.add(
                                new SimpleGrantedAuthority("ROLE_" + role)
                            );
                        }
                    }

                    // Option 2: If role is stored as a single string
                    String role = (String) claims.get("role");
                    if (role != null) {
                        authorities.add(
                            new SimpleGrantedAuthority("ROLE_" + role)
                        );
                    }

                    // If no roles found, you might want to add a default role or handle this case
                    if (authorities.isEmpty()) {
                        // Add default role or handle the case where no roles are present
                        authorities.add(
                            new SimpleGrantedAuthority("ROLE_USER")
                        );
                    }

                    UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities // This was null before - that's why authentication failed!
                        );

                    auth.setDetails(
                        new WebAuthenticationDetailsSource()
                            .buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (SignatureException e) {
                // Token inválido
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (Exception e) {
                // Handle other JWT parsing errors
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
