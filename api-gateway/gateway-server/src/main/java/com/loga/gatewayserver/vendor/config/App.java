package com.loga.gatewayserver.vendor.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    @Bean
    public CommandLineRunner init() {
        return  args -> {
            log.info("LoGA Gateway Server Initialization ---");
        };
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f-> f
                                .addRequestHeader("MyHeader","MyURI")
                                .addRequestParameter("Param", "MyValue"))
                        .uri("http://httpbin.org:80"))
                .route(p -> p
                        .path("/authentication-service/**")
                        .uri("lb://authentication-service"))
                .route(p -> p
                        .path("/customer-service/**")
                        .uri("lb://customer-service"))
                .route(p -> p
                        .path("/maintenance-service/**")
                        .uri("lb://maintenance-service"))
                .route(p -> p
                        .path("/intelligent-service/**")
                        .uri("lb://intelligent-service"))
                .route(p -> p
                        .path("/monitoring-service/**")
                        .uri("lb://monitoring-service"))
                .route(p -> p
                        .path("/report-service/**")
                        .uri("lb://report-service"))
                .build();
    }
}
