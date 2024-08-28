package com.nbcam.schedule_management_v2.controller;

import com.nbcam.schedule_management_v2.auth.JwtUtil;
import com.nbcam.schedule_management_v2.dto.request.LoginRequest;
import com.nbcam.schedule_management_v2.dto.request.UserCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.UserDeleteRequest;
import com.nbcam.schedule_management_v2.dto.request.UserUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.UserListResponse;
import com.nbcam.schedule_management_v2.dto.response.UserResponse;
import com.nbcam.schedule_management_v2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 생성")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest, HttpServletResponse response) {
        Long userId = userService.saveUser(userCreateRequest, response);
        return ResponseEntity.created(URI.create("/api/users/" + userId)).build();
    }

    @Operation(summary = "로그인 요청")
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        UserResponse userResponse = userService.login(loginRequest, response);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "유저 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable Long userId) {
        UserResponse userResponse = userService.findUserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "모든 유저 조회")
    @GetMapping
    public ResponseEntity<UserListResponse> findUsers() {
        List<UserResponse> userResponses = userService.findAll();
        return ResponseEntity.ok(UserListResponse.builder().userResponses(userResponses).build());
    }

    @Operation(summary = "유저 수정")
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "유저 삭제")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId, UserDeleteRequest userDeleteRequest) {
        userService.deleteUser(userId, userDeleteRequest);
        return ResponseEntity.noContent().build();
    }
}
