package kg.attractor.movie_riviewer.controller;

import kg.attractor.movie_riviewer.service.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String name = fileService.saveFile(file);
        return ResponseEntity.ok("File uploaded: " + name);
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<Resource> download(@PathVariable String name) throws IOException {
        java.nio.file.Path path = Paths.get("uploads").resolve(name);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        byte[] data = Files.readAllBytes(path);
        Resource resource = new ByteArrayResource(data);

        // Определяем тип контента (jpg или png)
        MediaType mediaType = name.toLowerCase().endsWith(".png") ?
                MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + name + "\"") // ЗАМЕНИЛИ attachment НА inline
                .body(resource);
    }
}