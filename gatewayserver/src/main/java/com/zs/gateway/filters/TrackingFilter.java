package com.zs.gateway.filters;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
//全局过滤器实现GlobalFilter接口，并且必须覆盖filter()方法
public class TrackingFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

    //过滤器中常用的方法封装在FilterUtils类中
    @Autowired
    FilterUtils filterUtils;

    //每次请求通过过滤器时执行的代码
    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        //使用通过参数传递给filter()方法的ServerWebExchange对象从请求中提取HTTP首部
        HttpHeaders requestHeaders =
                exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            logger.debug(
                    "tmx-correlation-id found in tracking filter: {}. ",
                    filterUtils.getCorrelationId(requestHeaders));
        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtils.setCorrelationId(exchange,correlationID);
            logger.debug(
                    "tmx-correlation-id generated in tracking filter: {}.",
                    correlationID);
        }
        filterUtils.setUserId(exchange,getUsername(requestHeaders));
        filterUtils.setAuthToken(exchange,filterUtils.getAuthToken(requestHeaders)
                .replace("Bearer ",""));
        return chain.filter(exchange);
    }

    private String getUsername(HttpHeaders requestHeaders){
        String username = "";
        if (filterUtils.getAuthToken(requestHeaders)!=null){
//从HTTP首部Authorization解析出令牌
            String authToken =
                    filterUtils.getAuthToken(requestHeaders)
                            .replace("Bearer ","");
            JSONObject jsonObj = decodeJWT(authToken);
            try {
                //从JWT中提取出preferred_username
                username = jsonObj.getString("preferred_username");
            }catch(Exception e) {logger.debug(e.getMessage());}
        }
        return username;
    }

    private JSONObject decodeJWT(String JWTToken) {
        String[] split_string = JWTToken.split("\\.");
        //使用Base64编码来解析令牌，传入对令牌进行签名的密钥
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        String body = new String(base64Url.decode(base64EncodedBody));
        //将JWT体解析成JSON对象以检索preferred_username
        JSONObject jsonObj = new JSONObject(body);
        return jsonObj;
    }
    private boolean isCorrelationIdPresent(HttpHeaders
                                                   requestHeaders) {
        //检查请求首部中是否有关联ID的辅助方法
        if (filterUtils.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    //生成关联ID的UUID值
    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}