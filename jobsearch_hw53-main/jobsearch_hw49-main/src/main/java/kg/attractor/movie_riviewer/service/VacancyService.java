package kg.attractor.movie_riviewer.service;

import kg.attractor.movie_riviewer.model.User;
import kg.attractor.movie_riviewer.model.Vacancy;
import kg.attractor.movie_riviewer.repository.UserRepository;
import kg.attractor.movie_riviewer.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;

    public List<Vacancy> getAllVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> getVacanciesByCategory(String category) {
        return vacancyRepository.findByCategory(category);
    }

    public void saveVacancyWithAuth(Vacancy vacancy, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        vacancy.setUserId(user.getId());
        vacancyRepository.save(vacancy);
        log.info("Вакансия успешно сохранена для пользователя: {}", email);
    }
}