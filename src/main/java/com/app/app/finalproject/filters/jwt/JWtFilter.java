package com.app.app.finalproject.filters.jwt;

import com.app.app.finalproject.environments.Environment;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Component
public class JWtFilter extends OncePerRequestFilter {

    private static final Set<String> UNPROTECTED_URLS = Set.of("/jwt/generate");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (!isNotProtectedUrl(request)) {
                var token = request.getHeader("Authorization").replace("Bearer ", "");
                var algorithm = Algorithm.HMAC256(Environment.JWT_SECRET);
                var verifier = JWT.require(algorithm).build();
                verifier.verify(token);
            }
        } catch (Exception ex) {
            response.setStatus(401);
            var body = new HashMap<String, Object>();
            body.put("message", "Unauthorized");
            body.put("statusCode", 401);
            body.put("body", new ArrayList<>());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            var writer = response.getWriter();
            writer.print(new Gson().toJson(body));
            writer.flush();
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isNotProtectedUrl(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        return UNPROTECTED_URLS.stream().anyMatch(requestUrl::matches);
    }

}
