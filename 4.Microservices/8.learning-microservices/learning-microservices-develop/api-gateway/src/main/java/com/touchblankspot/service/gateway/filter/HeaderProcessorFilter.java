package com.touchblankspot.service.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 */

@Configuration
@Slf4j
public class HeaderProcessorFilter implements GlobalFilter {

  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    /*
     * Executing before request processed by respective endpoint
     * */
    HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
    log.error("Headers {}", httpHeaders);
    System.out.println(httpHeaders);

    return chain.filter(exchange).then(Mono.fromRunnable(() -> {
      // processed once request completed by respective endpoint
      HttpStatusCode httpStatusCode = exchange.getResponse().getStatusCode();
      log.error("Status code {}", httpStatusCode);
    }));
  }
}
