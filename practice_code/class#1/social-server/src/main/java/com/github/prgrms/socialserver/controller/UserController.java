package com.github.prgrms.socialserver.controller;

import com.github.prgrms.socialserver.domain.User;
import com.github.prgrms.socialserver.domain.dto.CommonResponseDTO;
import com.github.prgrms.socialserver.domain.dto.UserJoinDTO;
import com.github.prgrms.socialserver.domain.dto.UserResponseDTO;
import com.github.prgrms.socialserver.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public CommonResponseDTO findUsers() {
        List<User> users = userServiceImpl.findUsers();
        return getResponse(true, users);
    }

    @GetMapping("/{userId}")
    public CommonResponseDTO findUser(@PathVariable("userId") Long userId) {
        User user = userServiceImpl.findById(userId).get();
        return getResponse(true, user);
    }

    @PostMapping("/join")
    public CommonResponseDTO save(@RequestBody UserJoinDTO userJoinDTO) {
        Optional<User> user = userServiceImpl.save(User.builder()
                .email(userJoinDTO.getPrincipal())
                .password(userJoinDTO.getCredentials())
                .build());

        if (user.isPresent()) {
            return getResponse(true, JoinResultMessage.가입완료);
        }
        return getResponse(false, JoinResultMessage.가입실패);
    }

    private CommonResponseDTO getResponse(Boolean success, Object response) {
        if (response instanceof JoinResultMessage) {
            return CommonResponseDTO.builder()
                    .success(success)
                    .response(response)
                    .build();

        } else if (response instanceof User) {
            return CommonResponseDTO.builder()
                    .success(true)
                    .response(userToUserResponse((User) response))
                    .build();

        } else {
            return CommonResponseDTO.builder()
                    .success(true)
                    .response(usersToUserResponse((List<User>) response))
                    .build();
        }
    }

    private List<UserResponseDTO> usersToUserResponse(List<User> users) {
        return users.stream().map(user -> UserResponseDTO.builder()
                .seq(user.getSeq())
                .principal(user.getEmail())
                .loginCount(user.getLoginCount())
                .lastLoginAt(user.getLastLoginAt())
                .createAt(user.getCreateAt())
                .build()).collect(Collectors.toList());
    }

    private UserResponseDTO userToUserResponse(User user) {
        return UserResponseDTO.builder()
                .seq(user.getSeq())
                .principal(user.getEmail())
                .loginCount(user.getLoginCount())
                .lastLoginAt(user.getLastLoginAt())
                .createAt(user.getCreateAt())
                .build();
    }
}
