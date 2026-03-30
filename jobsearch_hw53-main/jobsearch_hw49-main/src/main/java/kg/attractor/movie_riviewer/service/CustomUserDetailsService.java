package kg.attractor.movie_riviewer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    // CustomUserDetailsService.java
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Тайлер, проверяем вход для: {}", email);
        String userQuery = "SELECT email, password FROM users WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(userQuery, (rs, rowNum) ->
                            User.withUsername(rs.getString("email"))
                                    .password(rs.getString("password"))
                                    .authorities("ROLE_USER")
                                    // УДАЛИЛИ .enabled(true), чтобы проект скомпилировался
                                    .build()
                    , email);
        } catch (Exception e) {
            log.error("Тайлер, юзер не найден: {}", email);
            throw new UsernameNotFoundException("User not found: " + email);
        }
    }
}