package kg.attractor.movie_riviewer.repository;

import kg.attractor.movie_riviewer.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<Resume> findByUserId(Integer userId) {
        String sql = "SELECT * FROM resumes WHERE user_id = :userId";
        return jdbcTemplate.query(sql, Map.of("userId", userId), new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> findByCategory(String category) {
        String sql = "SELECT * FROM resumes WHERE category = :category";
        return jdbcTemplate.query(sql, Map.of("category", category), new BeanPropertyRowMapper<>(Resume.class));
    }
}