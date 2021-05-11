package uos.samsam.wing.service.padboxlog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.padboxlog.PadBoxLog;
import uos.samsam.wing.domain.padboxlog.PadBoxLogRepository;
import uos.samsam.wing.web.dto.StatisticsResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PadBoxLogServiceTest {

    @Autowired PadBoxLogRepository padBoxLogRepository;
    @Autowired PadBoxRepository padBoxRepository;
    @Autowired PadBoxLogService padBoxLogService;
    @Autowired JdbcTemplate jdbcTemplate;

    @AfterEach
    public void cleanup() {
        padBoxLogRepository.deleteAll();
        padBoxRepository.deleteAll();
    }

    @Test
    void 일주일_통계_계산() {
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build());

        String sql1 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -3, NOW()), "+ padBox.getId() +", -3)";
        String sql2 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -8, NOW()), "+ padBox.getId() +", -5)";
        String sql3 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -1, NOW()), "+ padBox.getId() +", -3)";
        String sql4 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -10, NOW()), "+ padBox.getId() +", -5)";
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        jdbcTemplate.execute(sql4);

        List<StatisticsResponseDto> responseDtoList = padBoxLogService.statistics(7);
        assertThat(responseDtoList.get(0).getPadBoxName()).isEqualTo(padBox.getName());
        assertThat(responseDtoList.get(0).getAmount()).isEqualTo(6);
    }

    @Test
    void 한달_통계_계산() {
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build());

        String sql1 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -25, NOW()), "+ padBox.getId() +", -5)";
        String sql2 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -41, NOW()), "+ padBox.getId() +", -3)";
        String sql3 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -4, NOW()), "+ padBox.getId() +", -5)";
        String sql4 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -99, NOW()), "+ padBox.getId() +", -3)";
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        jdbcTemplate.execute(sql4);

        List<StatisticsResponseDto> responseDtoList = padBoxLogService.statistics(30);
        assertThat(responseDtoList.get(0).getPadBoxName()).isEqualTo(padBox.getName());
        assertThat(responseDtoList.get(0).getAmount()).isEqualTo(10);
    }

    @Test
    void 통계량_테스트 () {
        String name1 = "padBox1";
        PadBox padBox1 = padBoxRepository.save(PadBox.builder()
                .latitude(37.583458)
                .longitude(127.058305)
                .address("서울특별시 동대문구 전농2동 89-14")
                .name(name1)
                .padAmount(0)
                .temperature(21.0)
                .humidity(35.0)
                .build());

        String sql1 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -2, NOW()), "+ padBox1.getId() +", 5)";
        String sql2 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -4, NOW()), "+ padBox1.getId() +", 3)";
        String sql3 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -4, NOW()), "+ padBox1.getId() +", -4)";
        String sql4 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -3, NOW()), "+ padBox1.getId() +", -9)";
        String sql5 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -3, NOW()), "+ padBox1.getId() +", 0)";
        jdbcTemplate.execute(sql1);
        jdbcTemplate.execute(sql2);
        jdbcTemplate.execute(sql3);
        jdbcTemplate.execute(sql4);
        jdbcTemplate.execute(sql5);

        String name2 = "padBox2";
        PadBox padBox2 = padBoxRepository.save(PadBox.builder()
                .latitude(37.583458)
                .longitude(127.058305)
                .address("서울특별시 동대문구 전농2동 89-14")
                .name(name2)
                .padAmount(0)
                .temperature(21.0)
                .humidity(35.0)
                .build());

        String sql6 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -2, NOW()), "+ padBox2.getId() +", 10)";
        String sql7 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -4, NOW()), "+ padBox2.getId() +", 15)";
        String sql8 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -4, NOW()), "+ padBox2.getId() +", -100)";
        String sql9 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -3, NOW()), "+ padBox2.getId() +", -150)";
        String sql10 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -3, NOW()), "+ padBox2.getId() +", 0)";
        jdbcTemplate.execute(sql6);
        jdbcTemplate.execute(sql7);
        jdbcTemplate.execute(sql8);
        jdbcTemplate.execute(sql9);
        jdbcTemplate.execute(sql10);

        List<StatisticsResponseDto> responseDtoList = padBoxLogService.statistics(30);
        assertThat(responseDtoList.get(0).getPadBoxName()).isEqualTo(padBox2.getName());
        assertThat(responseDtoList.get(0).getAmount()).isEqualTo(250);
        assertThat(responseDtoList.get(1).getPadBoxName()).isEqualTo(padBox1.getName());
        assertThat(responseDtoList.get(1).getAmount()).isEqualTo(13);
    }

    @Test
    void 오래된_로그_삭제() {
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build());

        String sql1 = "INSERT INTO PAD_BOX_LOG (created_date, box_id, diff_amount) values (DATEADD('DAY', -369, NOW()), "+ padBox.getId() +", -5)";
        jdbcTemplate.execute(sql1);

        List<StatisticsResponseDto> responseDtoList = padBoxLogService.statistics(30);
        List<PadBoxLog> padBoxLogList = padBoxLogRepository.findAll();
        assertThat(responseDtoList.get(0).getAmount()).isEqualTo(0);
        assertThat(padBoxLogList.size()).isEqualTo(0);
    }
}