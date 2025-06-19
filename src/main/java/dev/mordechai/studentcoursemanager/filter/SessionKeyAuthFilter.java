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

    private final String adminEndpoints = "/api/admin";
    private final String studentEndpoints = "/api/student";


    @Autowired
    public SessionKeyAuthFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String sessionKey = request.getHeader("Session-Key");
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        log.debug("Processing request: {} {} with session key: {}", method, requestURI,
                sessionKey != null ? "present" : "missing");


        if (requestURI.startsWith("/api/auth/")) {
            log.debug("Skipping authentication for auth endpoint: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        try {
            if (sessionKey == null || sessionKey.isEmpty()) {
                throw new EmptySessionException();
            }

            sessionService.validateSession(sessionKey);
            log.debug("Session key validated successfully");

            if (isStudentEndpoint(requestURI)) {
                log.debug("Validating student session for endpoint: {}", requestURI);
                sessionService.validateStudentSession(sessionKey);
                log.debug("Student session validated successfully");
            }

            if (isAdminEndpoint(requestURI)) {
                log.debug("Validating admin session for endpoint: {}", requestURI);
                sessionService.validateAdminSession(sessionKey);
                log.debug("Admin session validated successfully");
            }

            filterChain.doFilter(request, response);


        }catch (EmptySessionException ex) {
            handleAuthError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Request missing session key", ex);
        } catch (InvalidSessionKeyException ex) {
            handleAuthError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid session key", ex);
        } catch (SessionKeyExpiredException ex) {
            handleAuthError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Session key expired", ex);
        } catch (UnappropriatedSessionKeyException ex) {
            handleAuthError(response, HttpServletResponse.SC_FORBIDDEN,
                    "Insufficient permissions for this operation", ex);
        } catch (Exception ex) {
            handleAuthError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Internal server error", ex);
        }

    }

    private void handleAuthError(HttpServletResponse response, int statusCode,
                                 String message, Exception ex) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Create a proper JSON response
        String jsonResponse = String.format("{\"error\": \"%s\", \"timestamp\": \"%s\"}",
                message, java.time.Instant.now().toString());
        response.getWriter().write(jsonResponse);

        log.error("Authentication/Authorization error: {} - {}", message, ex.getMessage(), ex);
    }

    private boolean isAdminEndpoint(String requestURI) {
        return requestURI.startsWith(adminEndpoints);
    }

    private boolean isStudentEndpoint(String requestURI) {
        return requestURI.startsWith(studentEndpoints);
    }
} 