package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminLoginRequestDto {

    private String key;

    @Builder
    public AdminLoginRequestDto(String key) {
        this.key = key;
    }
}
