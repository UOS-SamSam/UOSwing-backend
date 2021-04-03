package uos.samsam.wing.domain.padboxlog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PadBoxLogRepositoryTest {

    @Autowired PadBoxLogRepository padBoxLogRepository;
    @Autowired PadBoxRepository padBoxRepository;

    @AfterEach
    public void cleanup() {
        padBoxLogRepository.deleteAll();
        padBoxRepository.deleteAll();
    }

    @Test
    public void 로그_생성_불러오기() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBox padBox = PadBox.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        padBoxRepository.save(padBox);

//        LocalDateTime now = LocalDateTime.now();
        LocalDateTime now = LocalDateTime.of(2021, 3, 27, 0, 0, 0);
        Integer usedAmount = 3;
        padBoxLogRepository.save(PadBoxLog.builder()
                .padBox(padBox)
                .usedAmount(usedAmount)
                .updatedDate(LocalDateTime.now())
                .build());

        // when
        List<PadBoxLog> padBoxLogList = padBoxLogRepository.findAll();

        // then
        PadBoxLog padBoxLog = padBoxLogList.get(0);
        assertThat(padBoxLog.getUsedAmount()).isEqualTo(usedAmount);
        assertThat(padBoxLog.getCreatedDate()).isAfter(now);
        assertThat(padBox.getId()).isEqualTo(padBoxLog.getPadBox().getId());
        assertThat(padBox.getName()).isEqualTo(padBoxLog.getPadBox().getName());
    }

}