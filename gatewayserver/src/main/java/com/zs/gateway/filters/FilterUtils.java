package com.zs.gateway.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "Authorization";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";
    public String getCorrelationId(HttpHeaders requestHeaders){
        if (requestHeaders.get(CORRELATION_ID) !=null) {
            List<String> header = requestHeaders.get(CORRELATION_ID);
            return header.stream().findFirst().get();
        } else{
            return null;
        }
    }


    public ServerWebExchange setRequestHeader(ServerWebExchange exchange,String name, String value) {
        return exchange.mutate().request(
                        exchange.getRequest().mutate()
                                .header(name, value)
                                .build())
                .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange,String correlationId) {
        return this.setRequestHeader(exchange,CORRELATION_ID,correlationId);
    }
    public final String getAuthToken(HttpHeaders requestHeaders){
        List<String> header = requestHeaders.get(AUTH_TOKEN);
        return header.stream().findFirst().get();
    }
    public ServerWebExchange setAuthToken(ServerWebExchange exchange,String correlationId) {
        return this.setRequestHeader(exchange,AUTH_TOKEN,correlationId);
    }
    public ServerWebExchange setUserId(ServerWebExchange exchange,String correlationId) {
        return this.setRequestHeader(exchange,USER_ID,correlationId);
    }
}
