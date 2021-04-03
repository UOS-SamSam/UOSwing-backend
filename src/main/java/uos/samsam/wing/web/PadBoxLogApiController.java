package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uos.samsam.wing.service.padboxlog.PadBoxLogService;
import uos.samsam.wing.web.dto.StatisticsResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/statistics")
@RestController
public class PadBoxLogApiController {

    private final PadBoxLogService padBoxLogService;

    @GetMapping("/{duration}")
    public List<StatisticsResponseDto> statistics(@PathVariable Integer duration) {
        return padBoxLogService.statistics(duration);
    }
}
