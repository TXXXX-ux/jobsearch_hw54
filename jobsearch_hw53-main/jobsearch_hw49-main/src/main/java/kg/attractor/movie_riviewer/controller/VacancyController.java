package kg.attractor.movie_riviewer.controller;

import jakarta.validation.Valid;
import kg.attractor.movie_riviewer.model.Vacancy;
import kg.attractor.movie_riviewer.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/vacancies")
    public List<Vacancy> getVacancies() {
        log.info("Тайлер, запрос на список вакансий");
        return vacancyService.getAllVacancies();
    }

    @PostMapping("/create")
    public String createVacancy(@Valid @RequestBody Vacancy vacancy, Authentication authentication) {
        String email = authentication.getName();
        log.info("Тайлер, юзер {} создает вакансию", email);
        vacancyService.saveVacancyWithAuth(vacancy, email);
        return "Вакансия успешно создана!";
    }
}