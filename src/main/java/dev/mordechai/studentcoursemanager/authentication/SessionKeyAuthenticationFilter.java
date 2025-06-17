package dev.mordechai.studentcoursemanager.authentication;

import dev.mordechai.studentcoursemanager.entity.Session;
import dev.mordechai.studentcoursemanager.entity.UserType;
import dev.mordechai.studentcoursemanager.service.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class SessionKeyAuthenticationFilter extends OncePerRequestFilter {

    private final SessionService sessionService;
    private final List<String> adminEndpoints = Arrays.asList(
            "/api/students",
            "/api/courses",
            "/api/admin/dashboard"
    );
    private final List<String> studentEndpoints = Arrays.asList(
            "/api/courses/registration"
    );

    public SessionKeyAuthenticationFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionKey = request.getHeader("Session-Key");
        String requestURI = request.getRequestURI();


        if (requestURI.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (sessionKey == null || sessionKey.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            Session session = sessionService.validateSession(sessionKey);

            if (isAdminEndpoint(requestURI) && !session.getUserType().equals(UserType.ADMIN)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            if (isStudentEndpoint(requestURI) && !session.getUserType().equals(UserType.STUDENT)) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isAdminEndpoint(String requestURI) {
        return adminEndpoints.stream().anyMatch(endpoint -> requestURI.startsWith(endpoint));
    }

    private boolean isStudentEndpoint(String requestURI) {
        return studentEndpoints.stream().anyMatch(endpoint -> requestURI.startsWith(endpoint));
    }
} 