package com.nbcam.schedule_management_v2.service;

import com.nbcam.schedule_management_v2.auth.AuthInfo;
import com.nbcam.schedule_management_v2.auth.JwtUtil;
import com.nbcam.schedule_management_v2.config.PasswordEncoder;
import com.nbcam.schedule_management_v2.dto.request.LoginRequest;
import com.nbcam.schedule_management_v2.dto.request.UserCreateRequest;
import com.nbcam.schedule_management_v2.dto.request.UserDeleteRequest;
import com.nbcam.schedule_management_v2.dto.request.UserUpdateRequest;
import com.nbcam.schedule_management_v2.dto.response.UserResponse;
import com.nbcam.schedule_management_v2.entity.Role;
import com.nbcam.schedule_management_v2.entity.User;
import com.nbcam.schedule_management_v2.exception.AuthenticationException;
import com.nbcam.schedule_management_v2.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public Long saveUser(UserCreateRequest userCreateRequest, HttpServletResponse response) {
        String email = userCreateRequest.getEmail();
        String password = passwordEncoder.encode(userCreateRequest.getPassword());
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new RuntimeException("중복된 사용자가 존재합니다.");
        }

        Role role = Role.USER;

        if (userCreateRequest.isAdmin()) {
            if (!ADMIN_TOKEN.equals(userCreateRequest.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = Role.ADMIN;
        }

        User user = User.builder()
                .username(userCreateRequest.getUsername())
                .email(userCreateRequest.getEmail())
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .password(password)
                .role(role)
                .build();
        Long userId = userRepository.save(user).getId();

        String token = jwtUtil.createToken(
                AuthInfo.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build()
        );

        jwtUtil.addJwtToHeader(token, response);
        return userId;
    }

    public UserResponse login(LoginRequest loginRequest, HttpServletResponse response) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(AuthenticationException::new);
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(
                AuthInfo.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build()
        );

        jwtUtil.addJwtToHeader(token, response);
        return UserResponse.from(user);
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
