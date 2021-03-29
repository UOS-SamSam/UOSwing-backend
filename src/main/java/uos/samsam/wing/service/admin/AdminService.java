package uos.samsam.wing.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uos.samsam.wing.domain.admin.Admin;
import uos.samsam.wing.domain.admin.AdminRepository;
import uos.samsam.wing.web.dto.AdminLoginRequestDto;
import uos.samsam.wing.web.dto.AdminSaveRequestDto;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createAdmin(AdminSaveRequestDto requestDto) {
        String encodedKey = passwordEncoder.encode(requestDto.getKey());
        adminRepository.save(
                Admin.builder()
                    .key(encodedKey)
                    .build());
    }

    @Transactional
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 어드민이 없습니다. id=" + id));

        adminRepository.delete(admin);
    }

    @Transactional
    public Boolean login(AdminLoginRequestDto requestDto) {
        List<Admin> adminList = adminRepository.findAll();
        for (Admin admin : adminList) {
            boolean isMatched = passwordEncoder.matches(requestDto.getKey(), admin.getKey());
            if(isMatched)
                return true;
        }
        return false;
    }
}
