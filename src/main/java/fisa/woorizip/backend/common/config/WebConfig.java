package fisa.woorizip.backend.common.config;

import fisa.woorizip.backend.common.PageArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PageArgumentResolver pageArgumentResolver;

    private static final String CLIENT_LOCALHOST = "http://localhost:3000";
    private static final String CLIENT_SECURE_LOCALHOST = "https://localhost:3000";

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageArgumentResolver);
    }

    private static final String CORS_ALLOWED_METHODS =
            "GET,POST,HEAD,PUT,PATCH,DELETE,TRACE,OPTIONS";

    public WebConfig(PageArgumentResolver pageArgumentResolver) {
        this.pageArgumentResolver = pageArgumentResolver;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods(CORS_ALLOWED_METHODS.split(","))
                .allowedOrigins(CLIENT_LOCALHOST, CLIENT_SECURE_LOCALHOST)
                .exposedHeaders(HttpHeaders.SET_COOKIE, HttpHeaders.LOCATION)
                .allowCredentials(true);
    }
}
