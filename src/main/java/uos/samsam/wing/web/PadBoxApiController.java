package uos.samsam.wing.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uos.samsam.wing.service.padbox.PadBoxService;
import uos.samsam.wing.web.dto.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/padbox")
@RestController
public class PadBoxApiController {

    private final PadBoxService padBoxService;

    @GetMapping
    public List<PadBoxListResponseDto> findAll() {
        return padBoxService.findAll();
    }

    @GetMapping("/{id}")
    public PadBoxResponseDto findById(@PathVariable Long id) {
        return padBoxService.findById(id);
    }

    @PatchMapping("/updateState/{id}")
    public Long updateState(@PathVariable Long id, @RequestBody PadBoxUpdateStateRequestDto requestDto) {
        return padBoxService.updateState(id, requestDto);
    }

    @PostMapping
    public Long save(@RequestBody PadBoxSaveRequestDto requestDto) {
        return padBoxService.save(requestDto);
    }

    @PatchMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PadBoxUpdateRequestDto requestDto) {
        return padBoxService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        padBoxService.delete(id);
    }

}
