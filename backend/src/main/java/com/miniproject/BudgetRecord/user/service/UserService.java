package com.miniproject.budgetrecord.user.service;

import com.miniproject.budgetrecord.user.domain.entity.User;
import com.miniproject.budgetrecord.user.dto.SignupRequest;
import com.miniproject.budgetrecord.user.dto.UserResponse;
import com.miniproject.budgetrecord.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse signup(SignupRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // TODO: 실제 운영 시 BCrypt 등으로 해시하세요.
        User user = User.builder()
                .email(req.getEmail())
                .passwordHash(req.getPassword())
                .nickname(req.getNickname())
                .build();

        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return toResponse(user);
    }

    private UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .email(u.getEmail())
                .nickname(u.getNickname())
                .build();
    }
}