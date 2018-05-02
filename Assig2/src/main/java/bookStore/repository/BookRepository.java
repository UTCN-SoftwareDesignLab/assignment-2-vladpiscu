package bookStore.repository;

import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer>{
    List<Book> findAllByTitleContaining(String title);
    List<Book> findAllByGenre(Genre genre);
    List<Book> findAllByAuthor(Author author);
    Book findById(int id);
    void deleteById(int id);
}
