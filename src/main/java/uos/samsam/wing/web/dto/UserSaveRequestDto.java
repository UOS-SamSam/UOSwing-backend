package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {

    private String email;
    private String password;

    @Builder
    public UserSaveRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
