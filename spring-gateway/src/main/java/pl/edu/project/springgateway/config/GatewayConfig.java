package pl.edu.project.springgateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                             @Value("${springgateway.boss.url}") String _bossUrl,
                             @Value("${springgateway.employee.url}") String _employeeUrl
    ){
        return builder.routes()
                .route("employee",route -> route
                        .path(
                                "/api/bosses/{bossId}/employees",
                                "/api/bosses/{bossId}/employees/{employeeId}"
                        )
                        .uri(_employeeUrl)
                )
                .route("boss",route -> route
                    .path(
                            "/api/bosses",
                            "/api/bosses/{bossId}"
                    )
                    .uri(_bossUrl)
                )
                .build();
    }
}
