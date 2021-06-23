package uos.samsam.wing.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.padboxlog.PadBoxLogRepository;
import uos.samsam.wing.web.dto.PadBoxSaveRequestDto;
import uos.samsam.wing.web.dto.PadBoxUpdateRequestDto;
import uos.samsam.wing.web.dto.PadBoxUpdateStateRequestDto;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * PadBoxApiControllerTest
 * PadBoxApiController를 테스트하기 위한 클래스입니다.
 * 각종 테스트 메소드가 정의되어 있습니다.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PadBoxApiControllerTest {

    @LocalServerPort
    private int port;
    private String preUrl;

    @Autowired private PadBoxRepository padBoxRepository;
    @Autowired private PadBoxLogRepository padBoxLogRepository;
    @Autowired private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        preUrl = "http://localhost:" + port + "/api/v1/padbox/";
    }

    @AfterEach
    void tearDown() {
        padBoxLogRepository.deleteAll();
        padBoxRepository.deleteAll();
    }

    @Test
    void 아무나_PadBox_모두_조회() throws Exception {
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());

        String url = preUrl;

        // when, then
        mvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    void 아무나_특정_PadBox_조회() throws Exception {
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());

        String url = preUrl + padBox.getId();

        // when, then
        mvc.perform(get(url))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void PadBox_추가() throws Exception {
        // given
        Double latitude = 3.3;    // 위도
        Double longitude = 3.3;   // 경도
        String address = "서울";     // 주소
        String name = "어딘가";        // 이름
        Integer padAmount = 999;      // 남은 수량
        Double temperature = 33.3; // 온도
        Double humidity = 33.3;    // 습도
        PadBoxSaveRequestDto requestDto = PadBoxSaveRequestDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        String url = preUrl;

        // when
        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<PadBox> padBoxList = padBoxRepository.findAll();
        assertThat(padBoxList.get(0).getName()).isEqualTo(name);
        assertThat(padBoxList.get(0).getTemperature()).isEqualTo(temperature);
        assertThat(padBoxList.get(0).getPadAmount()).isEqualTo(padAmount);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void PadBox_상태_수정() throws Exception {
        // given
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());

        Integer padAmount = 999;      // 남은 수량
        Double temperature = 33.3; // 온도
        Double humidity = 33.3;    // 습도
        PadBoxUpdateStateRequestDto requestDto = PadBoxUpdateStateRequestDto.builder()
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        String url = preUrl + "updateState/" + padBox.getId();

        // when
        mvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<PadBox> padBoxList = padBoxRepository.findAll();
        assertThat(padBoxList.get(0).getPadAmount()).isEqualTo(padAmount);
        assertThat(padBoxList.get(0).getTemperature()).isEqualTo(temperature);
        assertThat(padBoxList.get(0).getHumidity()).isEqualTo(humidity);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void PadBox_수정() throws Exception {
        // given
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());

        Double latitude = 9.9;    // 위도
        Double longitude = 9.9;   // 경도
        String address = "경기";     // 주소
        String name = "우리집";        // 이름
        PadBoxUpdateRequestDto requestDto = PadBoxUpdateRequestDto.builder()
                .longitude(longitude)
                .latitude(latitude)
                .address(address)
                .name(name)
                .build();
        String url = preUrl + padBox.getId();

        // when
        mvc.perform(patch(url).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<PadBox> padBoxList = padBoxRepository.findAll();
        assertThat(padBoxList.get(0).getLongitude()).isEqualTo(longitude);
        assertThat(padBoxList.get(0).getAddress()).isEqualTo(address);
        assertThat(padBoxList.get(0).getName()).isEqualTo(name);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void PadBox_삭제() throws Exception {
        // given
        PadBox padBox = padBoxRepository.save(PadBox.builder()
                .latitude(3.3)
                .longitude(3.3)
                .address("서울")
                .name("어딘가")
                .padAmount(999)
                .temperature(33.3)
                .humidity(33.3)
                .build());

        String url = preUrl + padBox.getId();

        // when
        mvc.perform(delete(url))
                .andExpect(status().isOk());

        // then
        Optional<PadBox> padBox1 = padBoxRepository.findById(padBox.getId());
        assertThat(padBox1).isEmpty();
    }
}