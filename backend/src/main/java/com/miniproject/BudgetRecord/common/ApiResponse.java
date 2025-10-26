package com.miniproject.budgetrecord.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ✅ 공통 API 응답 포맷
 *
 * 성공 시: {"success": true, "data": {...}}
 * 실패 시: GlobalExceptionHandler 등에서 {"success": false, "code": "...", "message": "..."}
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class ApiResponse<T> {

    private final boolean success;
    private final T data;

    // 성공 응답용 단축 메서드
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, data);
    }

    // 단순 성공(데이터 없는 경우)
    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(true, null);
    }

    // 실패 응답용 (필요 시 커스텀 예외 핸들러에서 사용)
    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<>(false, data);
    }
}
