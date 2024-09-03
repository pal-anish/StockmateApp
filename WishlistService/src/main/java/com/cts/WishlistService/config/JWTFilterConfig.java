package com.cts.WishlistService.config;

import com.cts.WishlistService.filter.WishlistServiceTokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTFilterConfig {
    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WishlistServiceTokenFilter());
        registrationBean.addUrlPatterns("/wishlist/*");
        return registrationBean;
    }
}
