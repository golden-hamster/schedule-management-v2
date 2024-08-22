package com.nbcam.schedule_management_v2.service;

import com.nbcam.schedule_management_v2.dto.request.UserCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.UserDeleteRequest;
import com.nbcam.schedule_management_v2.dto.request.UserUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.UserResponse;
import com.nbcam.schedule_management_v2.entity.User;
import com.nbcam.schedule_management_v2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long saveUser(UserCreateRequest userCreateRequest) {
        User user = User.builder()
                .username(userCreateRequest.getUsername())
                .email(userCreateRequest.getEmail())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .password(userCreateRequest.getPassword())
                .build();

        return userRepository.save(user).getId();
    }

    public UserResponse findUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        return UserResponse.from(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserResponse::from).toList();
    }

    public void updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        validatePassword(user, userUpdateRequest.getPassword());
        user.updateUsername(userUpdateRequest.getUsername());
        user.updateEmail(userUpdateRequest.getEmail());
        user.updatePassword(userUpdateRequest.getPassword());
        user.updateModifiedAt(LocalDateTime.now());
    }

    public void deleteUser(Long userId, UserDeleteRequest userDeleteRequest) {
        User user = userRepository.findById(userId).orElseThrow(RuntimeException::new);
        validatePassword(user, userDeleteRequest.getPassword());
        userRepository.delete(user);
    }

    public void validatePassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException();
        }
    }
}
