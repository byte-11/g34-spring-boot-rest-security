package uz.pdp.g34springbootsecurity.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.g34springbootsecurity.domain.Role;
import uz.pdp.g34springbootsecurity.provider.JwtProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String header = request.getHeader(AUTHORIZATION_HEADER);

        if (header == null || !header.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring(BEARER_PREFIX.length());

        if (!jwtProvider.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        final Claims claims = jwtProvider.parseClaims(token);
        final String username = claims.get("username", String.class);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, null, extractAuthorities(claims))
        );
        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> extractAuthorities(final Claims claims) {
        final String[] roles = claims.get("roles", String.class).split(",");

        if (roles.length > 0 && !roles[0].isEmpty()) {
            return Arrays.stream(roles).map(SimpleGrantedAuthority::new).toList();
        }
        return Collections.emptyList();
    }
}
