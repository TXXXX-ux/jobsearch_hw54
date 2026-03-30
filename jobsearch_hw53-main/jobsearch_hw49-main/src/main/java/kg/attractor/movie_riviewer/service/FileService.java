package kg.attractor.movie_riviewer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Slf4j
@Service
public class FileService {

    private final String UPLOAD_DIR = "uploads/";

    public String saveFile(MultipartFile file) throws IOException {
        log.info("Начало сохранения файла: {}", file.getOriginalFilename());

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            log.info("Файл успешно сохранен как: {}", fileName);
            return fileName;
        } catch (IOException e) {
            log.error("Ошибка при сохранении файла {}: {}", file.getOriginalFilename(), e.getMessage());
            throw e;
        }
    }
}