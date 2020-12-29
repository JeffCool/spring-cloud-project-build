package top.imau.microservice.shadowraze.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jeff
 * @date 2020/12/29
 */
@Component
public class CustomFilter implements GlobalFilter, Ordered {

    private static final ConcurrentHashMap<String, Integer> CACHE_MAP = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //简单模拟限流
        if (requestRateLimit(exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }

        if (!validToken(exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private boolean requestRateLimit(ServerWebExchange exchange) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        System.out.println("请求ip是:" + ip );
        Integer reqTimes = CACHE_MAP.get(ip);
        reqTimes = reqTimes == null ? 0 : reqTimes;
        System.out.println("当前请求次数：" + reqTimes);
        if (reqTimes != null && reqTimes > 5) {
            return true;
        }
        CACHE_MAP.put(ip, reqTimes + 1);
        return false;
    }

    private boolean validToken(ServerWebExchange exchange) {
        MultiValueMap<String, String> multiValueMap = exchange.getRequest().getQueryParams();
        System.out.println("请求参数是:" + multiValueMap);
        List<String> paramList = multiValueMap.get("token");
        if (paramList == null || paramList.size() == 0) {
            return false;
        }
        String token = paramList.get(0);
        if (StringUtils.isNotBlank(token)) {
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
