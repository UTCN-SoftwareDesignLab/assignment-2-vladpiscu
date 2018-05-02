package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    List<Book> findByGenre(String genre);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    Book findById(int id);
    boolean sellBook(Book book, int quantity);

    Book create(Book book);
    Book update(Book book);
    void remove(Book book);
    void remove(int id);
    void removeAll();
}
