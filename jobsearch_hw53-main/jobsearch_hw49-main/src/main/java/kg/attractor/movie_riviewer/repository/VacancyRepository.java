package kg.attractor.movie_riviewer.repository;

import kg.attractor.movie_riviewer.model.Vacancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {

    @Override
    List<Vacancy> findAll();

    List<Vacancy> findByCategory(String category);
}