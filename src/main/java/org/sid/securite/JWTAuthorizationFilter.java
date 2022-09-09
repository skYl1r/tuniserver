package org.sid.securite;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods","GET,POST,DELETE,PATCH");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else if (request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        else {

           String jwtToken = request.getHeader(SecurityParams.JWT_HEADER_NAME);
//           System.out.println("Token =" + jwtToken);
            if (jwtToken == null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            final JwtValidator validator = new JwtValidator();
            DecodedJWT token = null;
            try {
                token = validator.validate(jwtToken);
//                System.out.println( "Jwt is valid" );
            } catch (InvalidParameterException e) {
                System.out.println( "Jwt is invalid" );
                throw new InvalidParameterException(e.getMessage());
            }
            if(token == null) {
            	throw new InternalError("Please provide a token");
            }
//            System.out.println("JWT= " + token);
            String username = token.getClaim("preferred_username").asString();
            List<String> roles = token.getClaims().get("roles").asList(String.class);
            System.out.println("username = " + username);
            System.out.println("roles = " + roles);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            roles.forEach(rn -> {
                authorities.add(new SimpleGrantedAuthority(rn));
            });
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(user);
            filterChain.doFilter(request, response);

        }
    }
}
