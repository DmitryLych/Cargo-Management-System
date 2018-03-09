package lych.trucks.config;

import lych.trucks.application.interceptor.AccessControlHeaderInterceptor;
import lych.trucks.application.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Configuration for interceptors.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
        registry.addInterceptor(new AccessControlHeaderInterceptor());
    }
}
