package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> save(User user);
    Optional<User> findById(Long userId);
    List<User> findUsers();
}
