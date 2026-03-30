package kg.attractor.movie_riviewer.service;

import kg.attractor.movie_riviewer.model.Resume;
import kg.attractor.movie_riviewer.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public List<Resume> getResumesByUserId(Integer userId) {
        log.info("Получаем резюме для пользователя с id: {}", userId);
        List<Resume> resumes = resumeRepository.findByUserId(userId);
        log.info("Найдено резюме: {} для userId: {}", resumes.size(), userId);
        return resumes;
    }

    public List<Resume> getResumesByCategory(String category) {
        log.info("Ищем резюме по категории: {}", category);
        List<Resume> resumes = resumeRepository.findByCategory(category);
        log.info("Найдено резюме по категории '{}': {}", category, resumes.size());
        return resumes;
    }
}