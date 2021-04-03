package uos.samsam.wing.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtAuthenticationToken implements AuthenticationToken {
    private String token;
}
