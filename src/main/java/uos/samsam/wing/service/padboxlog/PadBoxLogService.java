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
        Map<PadBox, Integer> counter = new HashMap<>();

        List<PadBoxLog> padBoxLogList = padBoxLogRepository.findAll();
        for (PadBoxLog padBoxLog : padBoxLogList) {
            if (padBoxLog.getCreatedDate().isBefore(deleteLimit)) {
                padBoxLogRepository.delete(padBoxLog);
            } else if (padBoxLog.getCreatedDate().isAfter(begin) && padBoxLog.getUsedAmount() < 0) {
                PadBox padBox = padBoxLog.getPadBox();
                Integer beforeValue = counter.get(padBox);
                counter.put(padBox, beforeValue == null ? -padBoxLog.getUsedAmount() : beforeValue + -padBoxLog.getUsedAmount());
            }
        }

        List<StatisticsResponseDto> responseDtoList = new LinkedList<>();
        for (Map.Entry<PadBox, Integer> entry : counter.entrySet()) {
            responseDtoList.add(StatisticsResponseDto.builder()
                    .padBoxId(entry.getKey().getId())
                    .padBoxName(entry.getKey().getName())
                    .amount(entry.getValue())
                    .build());
        }
        Collections.sort(responseDtoList, new Comparator<StatisticsResponseDto>() {
            @Override
            public int compare(StatisticsResponseDto obj1, StatisticsResponseDto obj2) {
                return obj2.getAmount().compareTo(obj1.getAmount());
            }
        });
        return responseDtoList;
    }
}
