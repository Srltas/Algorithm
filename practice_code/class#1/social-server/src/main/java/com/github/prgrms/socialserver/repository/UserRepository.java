package com.github.prgrms.socialserver.repository;

import com.github.prgrms.socialserver.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);
    Optional<User> findById(Long userId);
    List<User> findAll();
}
