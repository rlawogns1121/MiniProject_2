package com.miniproject.budgetrecord.user.controller;

import com.miniproject.budgetrecord.common.ApiResponse;
import com.miniproject.budgetrecord.user.domain.entity.User;
import com.miniproject.budgetrecord.user.dto.SignupRequest;
import com.miniproject.budgetrecord.user.dto.UserResponse;
import com.miniproject.budgetrecord.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // 오늘은 데모 유저(로그인 없이) 사용. JWT 붙이면 SecurityContext에서 추출하도록 교체.
    private User mockUser() {
        return User.builder().id(1L).email("demo@test.com").nickname("데모").build();
    }

    /** 회원가입 */
    @PostMapping("/signup")
    public ApiResponse<UserResponse> signup(@RequestBody @Valid SignupRequest req) {
        return ApiResponse.ok(userService.signup(req));
    }

    /** 특정 사용자 조회 (관리/테스트용) */
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok(userService.getById(id));
    }

    /** 내 정보 조회 (현재는 mock) */
    @GetMapping("/me")
    public ApiResponse<UserResponse> me() {
        User me = mockUser();
        return ApiResponse.ok(
            UserResponse.builder()
                .id(me.getId())
                .email(me.getEmail())
                .nickname(me.getNickname())
                .build()
        );
    }
}