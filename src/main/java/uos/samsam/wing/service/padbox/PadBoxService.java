package uos.samsam.wing.service.padbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.domain.padboxlog.PadBoxLog;
import uos.samsam.wing.domain.padboxlog.PadBoxLogRepository;
import uos.samsam.wing.web.dto.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PadBoxService
 * 생리대함에 대한 비즈니스 로직을 정의하는 서비스 클래스입니다.
 */
@RequiredArgsConstructor
@Service
public class PadBoxService {

    private final PadBoxRepository padBoxRepository;
    private final PadBoxLogRepository padBoxLogRepository;

    /**
     * 생리대함 저장
     * @param requestDto http request dto
     * @return 생성된 생리대함의 id
     */
    @Transactional
    public Long save(PadBoxSaveRequestDto requestDto) {
        return padBoxRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 생리대함 수정
     * @param id 수정하는 생리대함의 id
     * @param requestDto http request dto
     * @return 수정된 생리대함의 id
     */
    @Transactional
    public Long update(Long id, PadBoxUpdateRequestDto requestDto) {
        PadBox padBox = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        padBox.update(requestDto.getLatitude(),
                requestDto.getLongitude(),
                requestDto.getAddress(),
                requestDto.getName());
        return id;
    }

    /**
     * 생리대함 상태 갱신
     * @param id 상태를 갱신하는 생리대함의 id
     * @param requestDto http request dto
     * @return 수정된 생리대함의 id
     */
    @Transactional
    public Long updateState(Long id, PadBoxUpdateStateRequestDto requestDto) {
        PadBox padBox = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        Integer diff = padBox.updateState(requestDto.getPadAmount(), requestDto.getTemperature(), requestDto.getHumidity());
        if (diff != 0) {
            padBoxLogRepository.save(PadBoxLog.builder()
                    .padBox(padBox)
                    .diffAmount(diff)
                    .build());
        }
        return id;
    }

    /**
     * 생리대함 삭제
     * @param id 삭제하고자 하는 생리대함의 id
     */
    @Transactional
    public void delete(Long id) {
        PadBox padBox = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        List<PadBoxLog> padBoxLogs = padBoxLogRepository.findAll();
        for (PadBoxLog padBoxLog : padBoxLogs) {
            if (padBoxLog.getPadBox().equals(padBox)) {
                padBoxLogRepository.delete(padBoxLog);
            }
        }
        padBoxRepository.delete(padBox);
    }

    /**
     * id로 조회
     * @param id 생리대함의 id
     * @return 생리대함 http response dto
     */
    @Transactional
    public PadBoxResponseDto findById(Long id) {
        PadBox entity = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        return new PadBoxResponseDto(entity);
    }

    /**
     * 생리대함 전체 조회
     * @return 생리대함 list response dto 리스트
     */
    @Transactional
    public List<PadBoxListResponseDto> findAll() {
        return padBoxRepository.findAll().stream()
                .map(PadBoxListResponseDto::new)
                .collect(Collectors.toList());
    }

}
