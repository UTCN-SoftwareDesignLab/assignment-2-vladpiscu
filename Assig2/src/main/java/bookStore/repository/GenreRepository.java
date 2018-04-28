package bookStore.repository;

import bookStore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByGenre(String genre);
    List<Genre> findByGenreContaining(String genre);
    Genre findById(int id);
}
