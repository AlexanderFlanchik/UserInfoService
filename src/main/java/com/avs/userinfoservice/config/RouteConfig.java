package com.avs.userinfoservice.config;

import com.avs.userinfoservice.dtos.UserDto;
import com.avs.userinfoservice.services.UserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouteConfig {

    @Bean
    public RouterFunction<ServerResponse> apiRoutes(UserInfoService userService) {
        return RouterFunctions.route(GET("/api/userinfo"),
                    serverRequest -> ServerResponse.ok().body(userService.GetUsers(), UserDto.class))
                .andRoute(GET("/api/userinfo/{userId}"),
                    serverRequest -> {
                        var userId = serverRequest.pathVariable("userId");
                        return userService.userDetails(userId)
                                .flatMap(details ->
                                        ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .bodyValue(details)
                                ).switchIfEmpty(ServerResponse.notFound().build());
                    });
    }

    @Bean
    public RouterFunction<ServerResponse> indexRouter(@Value("classpath:/index.html") Resource indexContent) {
        return RouterFunctions.route(GET("/").or(GET("/index.html")),
                    serverRequest -> ServerResponse.ok().contentType(MediaType.TEXT_HTML)
                            .body(BodyInserters.fromResource(indexContent)));
    }

    @Bean
    public RouterFunction<ServerResponse> resourcesRouter() {
        return RouterFunctions.resources("/css/*", new ClassPathResource("css/"))
                .and(RouterFunctions.resources("/js/*", new ClassPathResource("js/")));
    }
}