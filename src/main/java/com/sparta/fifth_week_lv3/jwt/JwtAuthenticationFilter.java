package com.sparta.fifth_week_lv3.jwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fifth_week_lv3.dto.login.LoginRequestDto;
import com.sparta.fifth_week_lv3.entity.AdminRoleEnum;
import com.sparta.fifth_week_lv3.security.AdminDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter { // 이렇게 하는 방식은 session 방식임. 이것을 구현하는 이유는 우리는 JWT를 이용하여 인증을 진행하기 때문임.
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/admin/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도");
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            return getAuthenticationManager().authenticate( // 클라이언트로부터 입력받은 유저 이름과 패스워드를 통해서 토큰을 만들고, Authentication
                    new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword(), null));
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 성공 및 JWT 생성"); // attemptAuthentication 메소드에서 받은 Authentication 객체가 인증되었는지 아닌지를 판단하여, 성공한 경우 수행함.
        String username = ((AdminDetailsImpl) authResult.getPrincipal()).getAdmin().getEmail();
        AdminRoleEnum role = ((AdminDetailsImpl) authResult.getPrincipal()).getAdmin().getAuthority();
        String token = jwtUtil.createToken(username, role);
        log.info("JWT " + token); // attemptAuthentication 메소드에서 받은 Authentication 객체가 인증되었는지 아닌지를 판단하여, 성공한 경우 수행함.
        jwtUtil.addJwtToCookie(token, response); // 로그인에 성공한 경우, JWT 토큰을 발급함.
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(401);
    }
}