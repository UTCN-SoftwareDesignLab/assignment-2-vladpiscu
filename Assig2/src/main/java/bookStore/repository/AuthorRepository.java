package bookStore.repository;

import bookStore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
    Author findByName(String name);
    Author findById(int id);
}
