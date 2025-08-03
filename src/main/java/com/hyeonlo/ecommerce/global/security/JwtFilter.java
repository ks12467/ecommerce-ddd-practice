package com.hyeonlo.ecommerce.global.security;

import com.hyeonlo.ecommerce.global.apipayload.status.ErrorStatus;
import com.hyeonlo.ecommerce.global.exception.BaseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = null;

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = jwtUtil.substringToken(authorizationHeader);
        } else {
            throw new BaseException(ErrorStatus.JWT_TOKEN_MISSING);
        }

        if (jwt != null && jwt.isBlank()) {
            try {
                Claims claims = jwtUtil.extractClaims(jwt);

                Long userId = Long.valueOf(claims.getSubject());
                String userName = claims.get("userName", String.class);
                String userRoleString = claims.get("userRole", String.class);

                if (userRoleString == null) {
                    response.sendError(HttpStatus.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다.");
                    return;
                }

                UserRole userRole = UserRole.of(userRoleString);

                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    String loginId = claims.get("loginId", String.class);

                    AuthUser authUser = new AuthUser(userId, loginId, userName, userRole);

                    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(authUser);

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            } catch (SecurityException | MalformedJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않는 JWT 서명입니다.");
            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "만료된 JWT 토큰입니다.");
            } catch (UnsupportedJwtException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "지원되지 않는 JWT 토큰입니다.");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        filterChain.doFilter(request, response);
    }
}