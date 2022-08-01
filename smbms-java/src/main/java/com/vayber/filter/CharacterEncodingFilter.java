package com.vayber.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-16");
        servletResponse.setCharacterEncoding("utf-16");

        filterChain.doFilter(servletRequest,servletResponse);



    }

    public void destroy() {

    }
}
