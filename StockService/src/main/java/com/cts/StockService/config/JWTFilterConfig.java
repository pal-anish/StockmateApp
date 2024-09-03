package com.cts.StockService.config;

import com.cts.StockService.filter.StockServiceTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTFilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new StockServiceTokenFilter());
        registrationBean.addUrlPatterns("/stocks/*");

        return registrationBean;
    }
}
