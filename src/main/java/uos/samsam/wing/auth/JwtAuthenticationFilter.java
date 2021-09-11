package uos.samsam.wing.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * JwtAutenticationFilter
 * JWT토큰을 검증하는 Filter입니다.
 * 필요한 경우 HTTP request를 이 클래스(필터)에 통과시켜 JWT토큰을 검증합니다.
 *
 * GenericFilterBean을 상속받습니다.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //HTTP header에서 JWT토큰을 추출합니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        //JWT토큰의 유효성 여부를 체크합니다.
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}