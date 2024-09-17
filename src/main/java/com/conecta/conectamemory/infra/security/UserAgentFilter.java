package com.conecta.conectamemory.infra.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class UserAgentFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private static final Logger logger = LoggerFactory.getLogger(UserAgentFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String userAgent = httpRequest.getHeader("User-Agent");

        if (userAgent == null || userAgent.isEmpty() || !isBrowserUserAgent(userAgent)) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            logger.error("Requisições fora do navegador não são permitidas");
            return;
        }


        chain.doFilter(request, response);
    }


    private boolean isBrowserUserAgent(String userAgent) {
        return userAgent.contains("Mozilla") || userAgent.contains("Chrome")
                || userAgent.contains("Safari") || userAgent.contains("Firefox");
    }
}
