package dev.mordechai.studentcoursemanager.filter;

import dev.mordechai.studentcoursemanager.exception.session.EmptySessionException;
import dev.mordechai.studentcoursemanager.exception.session.InvalidSessionKeyException;
import dev.mordechai.studentcoursemanager.exception.session.SessionKeyExpiredException;
import dev.mordechai.studentcoursemanager.exception.session.UnappropriatedSessionKeyException;
import dev.mordechai.studentcoursemanager.service.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class SessionKeyAuthFilter extends OncePerRequestFilter {

    private final SessionService sessionService;

    private final List<String> adminEndpoints = Arrays.asList(
            "/api/students",
            "/api/courses",
            "/api/admin/dashboard"
    );
    private final List<String> studentEndpoints = Arrays.asList(
            "/api/courses/registration"
    );

    @Autowired
    public SessionKeyAuthFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionKey = request.getHeader("Session-Key");
        String requestURI = request.getRequestURI();


        if (requestURI.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            if (sessionKey == null || sessionKey.isEmpty()) {
                throw new EmptySessionException();
            }
            sessionService.validateSession(sessionKey);

            if (isStudentEndpoint(requestURI)) {
                sessionService.validateStudentSession(sessionKey);
                filterChain.doFilter(request, response);
                return;
            }

            if (isAdminEndpoint(requestURI)) {
                sessionService.validateAdminSession(sessionKey);
                filterChain.doFilter(request, response);
                return;
            }

            filterChain.doFilter(request, response);
        }catch (EmptySessionException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String message = "request missing session key";
            response.getWriter().write(message);
            log.error("login error: "+message);


        }catch (InvalidSessionKeyException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String message = "invalid session-key";
            response.getWriter().write(message);
            log.error("login error: "+message);


        }catch (SessionKeyExpiredException ex){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String message = "session key expired";
            response.getWriter().write(message);
            log.error("login error: "+message);
        }catch (UnappropriatedSessionKeyException ex){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String message = "feature to do by user with this session-key are unauthorized";
            response.getWriter().write(message);
            log.error("login error: "+message);
        }catch (Exception ex){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String message = "server error";
            response.getWriter().write(message);
            log.error("login error: "+message);
        }


    }

    private boolean isAdminEndpoint(String requestURI) {
        return adminEndpoints.stream().anyMatch(endpoint -> requestURI.startsWith(endpoint));
    }

    private boolean isStudentEndpoint(String requestURI) {
        return studentEndpoints.stream().anyMatch(endpoint -> requestURI.startsWith(endpoint));
    }
} 