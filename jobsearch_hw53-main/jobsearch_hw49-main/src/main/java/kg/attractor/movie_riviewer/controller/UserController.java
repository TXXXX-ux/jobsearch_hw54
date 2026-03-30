package kg.attractor.movie_riviewer.controller;

import jakarta.validation.Valid;
import kg.attractor.movie_riviewer.model.User;
import kg.attractor.movie_riviewer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Добавили базовый префикс
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search")
    public User searchByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
}