package com.conecta.conectamemory.infra.config;

import com.conecta.conectamemory.infra.security.UserAgentFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<UserAgentFilter> userAgentFilter() {
        FilterRegistrationBean<UserAgentFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserAgentFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
}
}

