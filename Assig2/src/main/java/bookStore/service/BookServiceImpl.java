package bookStore.service;

import bookStore.dto.BookDto;
import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookStore.repository.AuthorRepository;
import bookStore.repository.BookRepository;
import bookStore.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByGenre(String genre) {
        List<Genre> bookGenres = genreRepository.findByGenreContaining(genre);
        List<Book> books = new ArrayList<>();
        for(Genre bookGenre : bookGenres)
            books.addAll(bookRepository.findAllByGenre(bookGenre));
        return books;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findAllByTitleContaining(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Author> bookAuthors = authorRepository.findByNameContaining(author);
        List<Book> books = new ArrayList<>();
        for(Author bookAuthor : bookAuthors)
            books.addAll(bookRepository.findAllByAuthor(bookAuthor));
        return books;
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean sellBook(Book book, int quantity) {

        if(book.getQuantity() >= quantity){
            book.setQuantity(book.getQuantity() - quantity);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        book.setId(book.getId());
        return bookRepository.save(book);
    }



    @Override
    public void remove(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void remove(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        bookRepository.deleteAll();
    }


}
