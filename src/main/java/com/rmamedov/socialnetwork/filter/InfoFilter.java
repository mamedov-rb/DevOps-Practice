package com.rmamedov.socialnetwork.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class InfoFilter extends OncePerRequestFilter {

    private final Environment environment;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {

        final String ip = request.getRemoteAddr();
        final String port = environment.getProperty("server.port");
        response.addHeader("Node-Address", String.format("%s:%s", ip, port));
        filterChain.doFilter(request, response);
    }

}
