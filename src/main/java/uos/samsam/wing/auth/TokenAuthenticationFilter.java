package uos.samsam.wing.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import uos.samsam.wing.domain.admin.Admin;
import uos.samsam.wing.domain.admin.AdminRepository;
import uos.samsam.wing.service.admin.AdminService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationTokenProvider authenticationTokenProvider;
    private final AdminRepository adminRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = authenticationTokenProvider.parseTokenString(request);
        if (authenticationTokenProvider.validateToken(token)) {
            Long userNo = authenticationTokenProvider.getTokenOwnerNo(token);
            try {
                Admin admin = adminRepository.findById(userNo).get();

                ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(admin, admin.getKey(), authorities);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (UsernameNotFoundException e) {
                throw new ServletException("유효하지 않은 인증토큰 입니다. 인증토큰 회원 정보 오류");
            }
        }
        filterChain.doFilter(request, response);
    }
}
