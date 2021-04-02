package uos.samsam.wing.service.padboxlog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padboxlog.PadBoxLog;
import uos.samsam.wing.domain.padboxlog.PadBoxLogRepository;
import uos.samsam.wing.web.dto.StatisticsResponseDto;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PadBoxLogService {

    private final PadBoxLogRepository padBoxLogRepository;

    @Transactional
    public List<StatisticsResponseDto> statistics(Integer duration) {
        LocalDateTime deleteLimit = LocalDateTime.now().minusDays(365);
        LocalDateTime begin = LocalDateTime.now().minusDays(duration);
        Map<String, Integer> counter = new TreeMap<String, Integer>(Collections.reverseOrder());

        List<PadBoxLog> padBoxLogList = padBoxLogRepository.findAll();
        for (PadBoxLog padBoxLog : padBoxLogList) {
            if (padBoxLog.getCreatedDate().isBefore(deleteLimit)) {
                padBoxLogRepository.delete(padBoxLog);
            } else if (padBoxLog.getCreatedDate().isAfter(begin) && padBoxLog.getUsedAmount() < 0) {
                String padBoxName = padBoxLog.getPadBox().getName();
                Integer beforeValue = counter.get(padBoxName);
                counter.put(padBoxName, beforeValue == null ? -padBoxLog.getUsedAmount() : beforeValue + -padBoxLog.getUsedAmount());
            }
        }

        List<StatisticsResponseDto> responseDtoList = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            responseDtoList.add(StatisticsResponseDto.builder()
                    .padBoxName(entry.getKey())
                    .amount(entry.getValue())
                    .build());
        }
        return responseDtoList;
    }
}
