package com.example.otp.generation.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;



@Configuration
public class CORSConfiguration {
    @Value("${allowedOrigin}")
    String corsFilterAllowedOrigins;
    @Value("${allowedMethods}")
    String corsFilterAlLowedMethods;
    @Value("${allowedHeaders}")
    String corsFilterAllowedHeaders;
    @Value("${allowedPathPattern}")
    String corsFilterAllowedPathPattern;

    @Bean
    public CorsFilter corsFilter() {
        var corsConfig = new CorsConfiguration();
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedOriginPatterns(delimitedStringToList(corsFilterAllowedOrigins));
        corsConfig.setAllowedHeaders(delimitedStringToList(corsFilterAllowedHeaders));
        corsConfig.setAllowedMethods(delimitedStringToList(corsFilterAlLowedMethods));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(corsFilterAllowedPathPattern, corsConfig);
        return new CorsFilter(source);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationßean(CorsFilter corsFilter) {
    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
   }

    private List<String> delimitedStringToList(String allowedList) {
        return Arrays.stream(allowedList.split(",")).map(String::trim).toList();

    }
}


