package com.nbcam.schedule_management_v2.controller;

import com.nbcam.schedule_management_v2.dto.request.UserCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.UserDeleteRequest;
import com.nbcam.schedule_management_v2.dto.request.UserUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.UserListResponse;
import com.nbcam.schedule_management_v2.dto.response.UserResponse;
import com.nbcam.schedule_management_v2.service.UserService;
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

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        Long userId = userService.saveUser(userCreateRequest);
        return ResponseEntity.created(URI.create("/api/users/" + userId)).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findUser(@PathVariable Long userId) {
        UserResponse userResponse = userService.findUserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<UserListResponse> findUsers() {
        List<UserResponse> userResponses = userService.findAll();
        return ResponseEntity.ok(UserListResponse.builder().userResponses(userResponses).build());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, UserUpdateRequest userUpdateRequest) {
        userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId, UserDeleteRequest userDeleteRequest) {
        userService.deleteUser(userId, userDeleteRequest);
        return ResponseEntity.noContent().build();
    }
}
