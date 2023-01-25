package com.example.gatewayservice.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.List;
import java.util.Objects;

@Component
public class CustomAuthFilter extends AbstractGatewayFilterFactory<CustomAuthFilter.Config> {

    @Value("${jwt.secret}")
    private String secretKey;

    public CustomAuthFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // Request Header 에 token 이 존재하지 않을때
            if(!request.getHeaders().containsKey("Authorization")){
                return handleUnAuthorize(exchange);
            }

            String jwt = request.getHeaders().get("Authorization").get(0).substring(7);

            //header에 토큰 정보 가져온다.
            byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).




            //옳게된 토큰이라면 header에 정보를 추가해줌

            return chain.filter(exchange);

        });

    }

    private Mono<Void> handleUnAuthorize(ServerWebExchange exchange){
        ServerHttpResponse response = exchange.getResponse();

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }


    public static class Config{

    }
}
