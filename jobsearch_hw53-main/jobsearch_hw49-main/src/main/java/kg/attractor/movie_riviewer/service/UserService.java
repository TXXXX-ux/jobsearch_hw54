package kg.attractor.movie_riviewer.service;

import kg.attractor.movie_riviewer.model.User;
import kg.attractor.movie_riviewer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        log.info("Получаем список всех пользователей");
        List<User> users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        log.info("Найдено пользователей: {}", users.size());
        return users;
    }

    public User getUserByEmail(String email) {
        log.info("Ищем пользователя по email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Пользователь с email {} не найден", email);
                    return new RuntimeException("Пользователь с email " + email + " не найден");
                });
    }

    public void save(User user) {
        log.info("Попытка регистрации пользователя с email: {}", user.getEmail());

        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("Email {} уже занят", user.getEmail());
            throw new RuntimeException("Email уже занят!");
        }

        // Шифруем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        log.info("Пользователь {} успешно зарегистрирован", user.getEmail());
    }
}