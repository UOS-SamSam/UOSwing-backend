package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.padbox.PadBoxService;
import uos.samsam.wing.web.dto.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PadBoxApiController {

    private final PadBoxService padBoxService;

    @GetMapping("/api/v1/padbox")
    public List<PadBoxListResponseDto> findAll() {
        return padBoxService.findAll();
    }

    @GetMapping("/api/v1/padbox/{id}")
    public PadBoxResponseDto findById(@PathVariable Long id) {
        return padBoxService.findById(id);
    }

    @PutMapping("/api/v1/padbox/updateState/{id}")
    public Long updateState(@PathVariable Long id, @RequestBody PadBoxUpdateStateRequestDto requestDto) {
        return padBoxService.updateState(id, requestDto);
    }

    @PostMapping("/api/v1/padbox/save")
    public Long save(@RequestBody PadBoxSaveRequestDto requestDto) {
        return padBoxService.save(requestDto);
    }

    @PutMapping("/api/v1/padbox/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody PadBoxUpdateRequestDto requestDto) {
        return padBoxService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/padbox/delete/{id}")
    public void delete(@PathVariable Long id) {
        padBoxService.delete(id);
    }

}
