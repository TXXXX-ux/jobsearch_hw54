package kg.attractor.movie_riviewer.controller;

import kg.attractor.movie_riviewer.model.Resume;
import kg.attractor.movie_riviewer.model.Vacancy;
import kg.attractor.movie_riviewer.service.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class ApplicantController {

    private final FileService fileService;

    public ApplicantController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/resumes")
    public ResponseEntity<String> createResume(@RequestBody Resume resume) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Резюме создано успешно");
    }

    @DeleteMapping("/resumes/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vacancies")
    public ResponseEntity<List<Vacancy>> findAllVacancies() {
        return ResponseEntity.ok(List.of());
    }

    @PostMapping("/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileService.saveFile(file);
            return ResponseEntity.ok("Аватар успешно загружен: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка загрузки");
        }
    }
}