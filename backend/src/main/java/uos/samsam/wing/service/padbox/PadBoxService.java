package uos.samsam.wing.service.padbox;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.padbox.PadBox;
import uos.samsam.wing.domain.padbox.PadBoxRepository;
import uos.samsam.wing.web.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PadBoxService {

    private final PadBoxRepository padBoxRepository;

    @Transactional
    public Long save(PadBoxSaveRequestDto requestDto) {
        return padBoxRepository.save(requestDto.toEntity()).getId();
    }

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

    @Transactional
    public Long updateState(Long id, PadBoxUpdateStateRequestDto requestDto) {
        PadBox padBox = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        padBox.updateState(requestDto.getPadAmount(), requestDto.getTemperature(), requestDto.getHumidity());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        PadBox padBox = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        padBoxRepository.delete(padBox);
    }

    public PadBoxResponseDto findById(Long id) {
        PadBox entity = padBoxRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("[id:" + id + "]해당 보관함이 없습니다."));
        return new PadBoxResponseDto(entity);
    }

    @Transactional
    public List<PadBoxListResponseDto> findAll() {
        return padBoxRepository.findAll().stream()
                .map(PadBoxListResponseDto::new)
                .collect(Collectors.toList());
    }

}
