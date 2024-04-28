package com.hrms.hrms_gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        String value = path.value();
        HttpHeaders headers = request.getHeaders();
        List<String> list = headers.get("Token");
        if(value.toLowerCase().indexOf("hrms_verify")!=0){
            //如果是登录请求则直接放行
            System.out.println("测试中");
           return chain.filter(exchange);
        }else{
            //获取的JWT字符串
            String s = list.get(0);
            System.out.println("测试中+1");
            boolean b = JwtUtils.checkToken(s);
            if(!b){
                //设置拦截
               return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 0;
    }
}