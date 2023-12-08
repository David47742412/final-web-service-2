package com.app.app.finalproject.controllers.jwt_generate;

import com.app.app.finalproject.environments.Environment;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController()
@RequestMapping("/jwt")
public class JwtGenerate {

    @GetMapping()
    public Map<String, String> getPayload() {
        var map = new HashMap<String, String>();
        map.put("name", "John Doe");
        map.put("email", "dcastro@codeglobal.co");
        return map;
    }

    @GetMapping("/generate")
    public Map<String, String> getPayload2() {
        var map = new HashMap<String, String>();
        var algorithm = Algorithm.HMAC256(Environment.JWT_SECRET);
        map.put("token", JWT.create()
                .withClaim("userId", "123")
                .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(algorithm));
        return map;
    }

}
