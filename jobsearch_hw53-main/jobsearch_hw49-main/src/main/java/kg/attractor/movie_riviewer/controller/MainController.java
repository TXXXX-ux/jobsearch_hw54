package kg.attractor.movie_riviewer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String index() {
        return "<h1>Система поиска работы запущена</h1>" +
                "<ul>" +
                "<li><a href='/users'>Список пользователей</a></li>" +
                "<li><a href='/vacancies'>Вакансии</a></li>" +
                "</ul>";
    }
}