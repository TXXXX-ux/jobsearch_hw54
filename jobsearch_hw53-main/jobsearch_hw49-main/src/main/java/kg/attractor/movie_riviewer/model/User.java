package kg.attractor.movie_riviewer.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private Integer id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String surname;

    @Min(value = 14, message = "Регистрация доступна только с 14 лет")
    @Max(value = 100, message = "Указан некорректный возраст")
    private Integer age;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Неверный формат почты")
    private String email;

    @Size(min = 6, message = "Пароль должен быть от 6 символов")
    private String password;

    @Column("PHONE_NUMBER")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Неверный формат номера телефона")
    private String phoneNumber;

    private String avatar;

    @Column("ACCOUNT_TYPE")
    private String accountType;
}