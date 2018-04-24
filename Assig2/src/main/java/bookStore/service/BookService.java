package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    List<Book> findByGenre(String genre);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    boolean sellBook(BookDto book, int quantity);

    Book create(BookDto book);
    Book update(BookDto book);
    void remove(BookDto book);
    void removeAll();
}
