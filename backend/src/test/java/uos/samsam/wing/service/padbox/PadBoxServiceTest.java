package uos.samsam.wing.service.padbox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.web.dto.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PadBoxServiceTest {

    @Autowired PadBoxRepository padBoxRepository;
    @Autowired PadBoxService padBoxService;

    @AfterEach
    void cleanup() {
        padBoxRepository.deleteAll();
    }

    @Test
    void PadBox_저장_테스트() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBoxSaveRequestDto requestDto = PadBoxSaveRequestDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();

        // when
        padBoxService.save(requestDto);

        // then
        List<PadBox> padBoxList = padBoxRepository.findAll();
        PadBox padBox = padBoxList.get(0);
        assertThat(padBox.getLatitude()).isEqualTo(latitude);
        assertThat(padBox.getLongitude()).isEqualTo(longitude);
        assertThat(padBox.getAddress()).isEqualTo(address);
        assertThat(padBox.getName()).isEqualTo(name);
        assertThat(padBox.getPadAmount()).isEqualTo(padAmount);
        assertThat(padBox.getTemperature()).isEqualTo(temperature);
        assertThat(padBox.getHumidity()).isEqualTo(humidity);
    }

    @Test
    void PadBox_수정_테스트() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBoxSaveRequestDto saveRequestDto = PadBoxSaveRequestDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        Long id = padBoxService.save(saveRequestDto);

        Double nextLat = 38.57;
        Double nextLon = 128.0;
        String nextAddress = "경기도 고양시 덕양구";
        String nextName = "23세기관";
        PadBoxUpdateRequestDto updateRequestDto = PadBoxUpdateRequestDto.builder()
                .latitude(nextLat)
                .longitude(nextLon)
                .address(nextAddress)
                .name(nextName)
                .build();

        // when
        padBoxService.update(id, updateRequestDto);

        // then
        List<PadBox> padBoxList = padBoxRepository.findAll();
        PadBox padBox = padBoxList.get(0);
        assertThat(padBox.getLatitude()).isEqualTo(nextLat);
        assertThat(padBox.getLongitude()).isEqualTo(nextLon);
        assertThat(padBox.getAddress()).isEqualTo(nextAddress);
        assertThat(padBox.getName()).isEqualTo(nextName);
        assertThat(padBox.getPadAmount()).isEqualTo(padAmount);
        assertThat(padBox.getTemperature()).isEqualTo(temperature);
        assertThat(padBox.getHumidity()).isEqualTo(humidity);
    }

    @Test
    void PadBox_상태_갱신_테스트() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBoxSaveRequestDto saveRequestDto = PadBoxSaveRequestDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        Long id = padBoxService.save(saveRequestDto);

        Integer nextPadAmount = 10;
        Double nextTemperature = 23.0;
        Double nextHumidity = 34.0;
        PadBoxUpdateStateRequestDto updateRequestDto = PadBoxUpdateStateRequestDto.builder()
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();

        // when
        padBoxService.updateState(id, updateRequestDto);

        // then
        List<PadBox> padBoxList = padBoxRepository.findAll();
        PadBox padBox = padBoxList.get(0);
        assertThat(padBox.getLatitude()).isEqualTo(latitude);
        assertThat(padBox.getLongitude()).isEqualTo(longitude);
        assertThat(padBox.getAddress()).isEqualTo(address);
        assertThat(padBox.getName()).isEqualTo(name);
        assertThat(padBox.getPadAmount()).isEqualTo(nextPadAmount);
        assertThat(padBox.getTemperature()).isEqualTo(nextTemperature);
        assertThat(padBox.getHumidity()).isEqualTo(nextHumidity);
    }

    @Test
    void PadBox_삭제_테스트() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBoxSaveRequestDto saveRequestDto = PadBoxSaveRequestDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        Long id = padBoxService.save(saveRequestDto);

        //when
        padBoxService.delete(id);

        //then
        List<PadBoxListResponseDto> responseDtoList = padBoxService.findAll();
        assertThat(responseDtoList.size()).isEqualTo(0);
    }

    @Test
    void PadBox_조회_테스트() {
        // given
        Double latitude = 37.583458;
        Double longitude = 127.058305;
        String address = "서울특별시 동대문구 전농2동 89-14";
        String name = "21세기관";
        Integer padAmount = 0;
        Double temperature = 21.0;
        Double humidity = 35.0;
        PadBoxSaveRequestDto saveRequestDto = PadBoxSaveRequestDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .name(name)
                .padAmount(padAmount)
                .temperature(temperature)
                .humidity(humidity)
                .build();
        Long id = padBoxService.save(saveRequestDto);

        // when
        PadBoxResponseDto responseDto = padBoxService.findById(id);

        // then
        assertThat(responseDto.getLatitude()).isEqualTo(latitude);
        assertThat(responseDto.getLongitude()).isEqualTo(longitude);
        assertThat(responseDto.getAddress()).isEqualTo(address);
        assertThat(responseDto.getName()).isEqualTo(name);
        assertThat(responseDto.getPadAmount()).isEqualTo(padAmount);
        assertThat(responseDto.getTemperature()).isEqualTo(temperature);
        assertThat(responseDto.getHumidity()).isEqualTo(humidity);
    }

    @Test
    void PadBox_전체_조회_테스트() {
        // given
        Double latitude1 = 37.583458;
        Double longitude1 = 127.058305;
        String address1 = "서울특별시 동대문구 전농2동 89-14";
        String name1 = "21세기관";
        Integer padAmount1 = 0;
        Double temperature1 = 21.0;
        Double humidity1 = 35.0;
        PadBoxSaveRequestDto saveRequestDto1 = PadBoxSaveRequestDto.builder()
                .latitude(latitude1)
                .longitude(longitude1)
                .address(address1)
                .name(name1)
                .padAmount(padAmount1)
                .temperature(temperature1)
                .humidity(humidity1)
                .build();
        Double latitude2 = 38.583458;
        Double longitude2 = 128.058305;
        String address2 = "서울특별시 동대문구";
        String name2 = "22세기관";
        Integer padAmount2 = 10;
        Double temperature2 = 23.0;
        Double humidity2 = 34.0;
        PadBoxSaveRequestDto saveRequestDto2 = PadBoxSaveRequestDto.builder()
                .latitude(latitude2)
                .longitude(longitude2)
                .address(address2)
                .name(name2)
                .padAmount(padAmount2)
                .temperature(temperature2)
                .humidity(humidity2)
                .build();

        Long id1 = padBoxService.save(saveRequestDto1);
        Long id2 = padBoxService.save(saveRequestDto2);

        // when
        List<PadBoxListResponseDto> responseDtoList = padBoxService.findAll();
        assertThat(responseDtoList.size()).isEqualTo(2);
        List<String> names = new ArrayList<>();
        names.add(name1);
        names.add(name2);
        assertThat(responseDtoList.get(0)).isNotEqualTo(responseDtoList.get(1));
        assertTrue(names.contains(responseDtoList.get(0).getName()));
        assertTrue(names.contains(responseDtoList.get(1).getName()));
    }
}