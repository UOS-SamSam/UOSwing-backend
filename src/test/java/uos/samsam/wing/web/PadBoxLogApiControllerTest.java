package uos.samsam.wing.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.padboxlog.PadBoxLogRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PadBoxLogApiControllerTest {

    @LocalServerPort
    private int port;
    private String preUrl;

    @Autowired private PadBoxRepository padBoxRepository;
    @Autowired private PadBoxLogRepository padBoxLogRepository;
    @Autowired private WebApplicationContext context;
    @Autowired JdbcTemplate jdbcTemplate;
    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        preUrl = "http://localhost:" + port + "/api/v1/statistics/";
    }

    @AfterEach
    void tearDown() {
        padBoxLogRepository.deleteAll();
        padBoxRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void 통계_계산() throws Exception {
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

        String sql = "INSERT INTO PAD_BOX_LOG (created_date, box_id, used_amount) values (DATEADD('DAY', -3, NOW()), "+ padBox.getId() +", -3)";
        jdbcTemplate.execute(sql);

        String url = preUrl + "30";

        // when, then
        mvc.perform(get(url))
                .andExpect(status().isOk());
    }
}