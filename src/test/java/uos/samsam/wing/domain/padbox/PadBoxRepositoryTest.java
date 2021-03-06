package uos.samsam.wing.domain.padbox;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Column;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PadBoxRepositoryTest
 * PadBoxRepository를 테스트하기 위한 클래스입니다.
 * 각종 테스트 메소드가 정의되어 있습니다.
 */
@SpringBootTest
class PadBoxRepositoryTest {

    @Autowired PadBoxRepository padBoxRepository;

    @AfterEach
    public void cleanup() {
        padBoxRepository.deleteAll();
    }

    @Test
    public void PadBox_저장_불러오기() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        padBoxRepository.save(PadBox.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build());

        // when
        List<PadBox> padBoxList = padBoxRepository.findAll();

        // then
        PadBox padBox = padBoxList.get(0);
        assertThat(padBox.getLatitude()).isEqualTo(latitude);
        assertThat(padBox.getLongitude()).isEqualTo(longitude);
        assertThat(padBox.getAddress()).isEqualTo(address);
        assertThat(padBox.getName()).isEqualTo(name);
        assertThat(padBox.getPadAmount()).isEqualTo(padAmount);
        assertThat(padBox.getTemperature()).isEqualTo(temperature);
        assertThat(padBox.getHumidity()).isEqualTo(humidity);
    }
}