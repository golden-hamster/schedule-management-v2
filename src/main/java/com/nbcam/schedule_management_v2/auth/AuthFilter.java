package com.nbcam.schedule_management_v2.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbcam.schedule_management_v2.entity.User;
import com.nbcam.schedule_management_v2.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class AuthFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String url = request.getRequestURI();

        if (StringUtils.hasText(url) && (url.startsWith("/api/users") || url.startsWith("/css") || url.startsWith("/js"))
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            String tokenValue = jwtUtil.getTokenFromRequest(request);

            if (StringUtils.hasText(tokenValue)) {
                String token = jwtUtil.substringToken(tokenValue);

                if (!jwtUtil.validateToken(token)) {
                    setErrorResponse(response, ErrorCode.INVALID_TOKEN);
                    return;
                }

                AuthInfo authInfo = jwtUtil.getAuthInfoFromToken(token);
                User user = userRepository.findById(authInfo.getUserId()).orElseThrow(() -> new RuntimeException("User Not Found Error"));
                request.setAttribute("user", user);
            } else {
                setErrorResponse(response, ErrorCode.TOKEN_NOT_FOUND);
                return;
            }

            chain.doFilter(request, response);
        }
    }

    private void setErrorResponse(
            HttpServletResponse response,
            ErrorCode errorCode
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode(), errorCode.getMessage());
        try{
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Data
    public static class ErrorResponse{
        private final Integer code;
        private final String message;
    }
}
