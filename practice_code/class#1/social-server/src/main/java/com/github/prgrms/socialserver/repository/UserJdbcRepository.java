package com.github.prgrms.socialserver.repository;

import com.github.prgrms.socialserver.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class UserJdbcRepository implements UserRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Optional<User> save(User user) {
        String sql = "insert into users(email, password) values(:email, :password)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, param, keyHolder, new String[] {"seq"});
        Long key = keyHolder.getKey().longValue();

        return findById(key);
    }

    public Optional<User> findById(Long userId) {
        String sql = "select seq, email, password, login_count, last_login_at, create_at" +
                " from users where seq=:seq";

        Map<String, Object> param = Map.of("seq", userId);
        User user = jdbcTemplate.queryForObject(sql, param, userRowMapper());

        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        String sql = "select seq, email, password, login_count, last_login_at, create_at" +
                " from users";

        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return ((rs, rowNum) -> {
            return User.builder()
                    .seq(rs.getLong("seq"))
                    .email(rs.getString("email"))
                    .password(rs.getString("password"))
                    .loginCount(rs.getInt("login_count"))
                    .lastLoginAt(timeStampToLocalDateTime(rs.getTimestamp("last_login_at")))
                    .createAt(timeStampToLocalDateTime(rs.getTimestamp("create_at")))
                    .build();
        });
    }

    private LocalDateTime timeStampToLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }
}
