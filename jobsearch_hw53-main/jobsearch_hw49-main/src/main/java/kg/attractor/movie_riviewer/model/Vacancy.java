package kg.attractor.movie_riviewer.model;



import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("VACANCIES")
public class Vacancy {
    @Id
    private Integer id;
    private Integer userId;

    @NotBlank(message = "Категория вакансии должна быть указана")
    private String category;

    @NotBlank(message = "Заголовок вакансии обязателен")
    @Size(min = 5, max = 100, message = "Заголовок должен быть от 5 до 100 символов")
    private String title;

    @NotBlank(message = "Описание вакансии не может быть пустым")
    @Size(min = 10, message = "Описание слишком короткое")
    @Column("INFO")
    private String description;
}