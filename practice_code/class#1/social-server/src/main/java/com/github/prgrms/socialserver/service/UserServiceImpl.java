package com.github.prgrms.socialserver.service;

import com.github.prgrms.socialserver.domain.User;
import com.github.prgrms.socialserver.repository.UserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserJdbcRepository userJDBCRepository;

    @Autowired
    public UserServiceImpl(UserJdbcRepository userJDBCRepository) {
        this.userJDBCRepository = userJDBCRepository;
    }

    @Transactional
    public Optional<User> save(User user) {
        return userJDBCRepository.save(user);
    }

    public Optional<User> findById(Long userId) {
        return userJDBCRepository.findById(userId);
    }

    public List<User> findUsers() {
        return userJDBCRepository.findAll();
    }
}
