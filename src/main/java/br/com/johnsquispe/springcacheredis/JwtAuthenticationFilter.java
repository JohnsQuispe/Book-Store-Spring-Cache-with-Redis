package br.com.johnsquispe.springcacheredis;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER_VALUE = "Authorization";

    private final JwtTokenManager tokenManager;

    public JwtAuthenticationFilter(JwtTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String jwtToken = this.getTokenFronRequest(request);

        if (this.tokenManager.isValid(jwtToken)) {

            final UserDetails userDetails = tokenManager.toUserDetails(jwtToken);

            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }

        filterChain.doFilter(request, response);

    }

    private String getTokenFronRequest (HttpServletRequest httpServletRequest) {

        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER_VALUE);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN_PREFIX)) {
            return bearerToken.substring(BEARER_TOKEN_PREFIX.length());
        }

        return null;

    }

}
