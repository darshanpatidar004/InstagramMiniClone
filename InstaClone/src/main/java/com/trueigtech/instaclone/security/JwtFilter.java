
package com.trueigtech.instaclone.security;
import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     private final String SECRET = "mysecretkey123";

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {
// String path = request.getServletPath();

// if (path.equals("/api/auth/login") || path.equals("/api/auth/signup")) {
//     filterChain.doFilter(request, response);
//     return;
// }


//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);

//             Claims claims = Jwts.parser()
//                     .setSigningKey(SECRET)
//                     .parseClaimsJws(token)
//                     .getBody();

//             String email = claims.getSubject();

//             UsernamePasswordAuthenticationToken auth =
//                     new UsernamePasswordAuthenticationToken(
//                             email, null, Collections.emptyList());

//             auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//             SecurityContextHolder.getContext().setAuthentication(auth);
//         }

//         filterChain.doFilter(request, response);
//     }
// }


@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String SECRET = "mysecretkey123";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/api/auth/");
    }

//     @Override
//     protected void doFilterInternal(
//             HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain
//     ) throws ServletException, IOException {
//           if (request.getServletPath().startsWith("/api/auth")) {
//     filterChain.doFilter(request, response);
//     return;
// }

//         String header = request.getHeader("Authorization");

//         if (header != null && header.startsWith("Bearer ")) {
//             String token = header.substring(7);

//             Claims claims = Jwts.parser()
//                     .setSigningKey(SECRET)
//                     .parseClaimsJws(token)
//                     .getBody();

//             String email = claims.getSubject();

//             UsernamePasswordAuthenticationToken auth =
//                     new UsernamePasswordAuthenticationToken(
//                             email, null, Collections.emptyList());

//             SecurityContextHolder.getContext().setAuthentication(auth);
//         }

//         filterChain.doFilter(request, response);
//     }
// }


@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    if (request.getServletPath().startsWith("/api/auth")) {
        filterChain.doFilter(request, response);
        return;
    }

    String header = request.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
        String token = header.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey("mysecretkey123")
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        email, null, Collections.emptyList());

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);
}
}