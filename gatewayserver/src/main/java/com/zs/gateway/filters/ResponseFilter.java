package com.zs.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class ResponseFilter {

    final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Autowired
    FilterUtils filterUtils;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                //获取原始HTTP请求中传入的关联ID
                String correlationId =
                        filterUtils.
                                getCorrelationId(requestHeaders);
                logger.debug(
                        "Adding the correlation id to the outbound headers. {}",
                        correlationId);
                //将关联ID注入响应中
                exchange.getResponse().getHeaders().
                        add(FilterUtils.CORRELATION_ID,
                correlationId);
                //记录传出的请求URI，这样你就有了“书挡”，它将显示进入网关的用户请求的传入和传出条目
                logger.debug("Completing outgoing request for {}.",exchange.getRequest().getURI());
            }));
        };
    }
}