package bookStore.repository;

import bookStore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByGenre(String genre);
    Genre findById(int id);
}
