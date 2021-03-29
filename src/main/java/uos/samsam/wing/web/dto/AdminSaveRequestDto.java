package uos.samsam.wing.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminSaveRequestDto {

    private String key;

    @Builder
    public AdminSaveRequestDto(String key) {
        this.key = key;
    }
}
