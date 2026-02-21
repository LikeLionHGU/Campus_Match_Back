package com.pigs.holiday.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pigs.holiday.exception.InvalidTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

public class FilterExceptionHandlerFilter extends OncePerRequestFilter {


	/**
     *  TokenExpiredException 핸들링을 위한 필터
	 *  상태코드 UNAUTHORIZED(401)을 response에 담아 리턴한다
	 */
    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        } catch (TokenExpiredException e){
            System.out.println("filter UNAUTHORIZED.");

            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); // application/json

            try{
                response.getWriter().write("Invalid Access Token.");
            }catch (IOException i){
                i.printStackTrace();
            }
        }
    }
    */

    private final ObjectMapper objectMapper;

    public FilterExceptionHandlerFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);

        } catch (TokenExpiredException e) {
            write401(response, "TOKEN_EXPIRED", "Access token expired.");

        } catch (JWTVerificationException e) {
            // AccessToken/RefreshToken 검증 실패
            write401(response, "TOKEN_INVALID", "Invalid token.");

        } catch (InvalidTokenException e) {
            // 커스텀 토큰 예외(Withdrawn 등)
            write401(response, "TOKEN_INVALID", e.getMessage());
        }
    }

    private void write401(HttpServletResponse response, String code, String message) throws IOException {
        if (response.isCommitted()) return;

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), Map.of(
                "code", code,
                "message", message
        ));
    }
}