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
        Genre bookGenre = genreRepository.findByGenre(genre);
        return bookRepository.findAllByGenre(bookGenre);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findAllByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Author bookAuthor = authorRepository.findByName(author);
        return bookRepository.findAllByAuthor(bookAuthor);
    }

    @Override
    public boolean sellBook(BookDto bookDto, int quantity) {
        Book book = getBook(bookDto);
        if(book.getQuantity() >= quantity){
            book.setQuantity(book.getQuantity() - quantity);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public Book create(BookDto book) {
        Book b = getBook(book);
        return bookRepository.save(b);
    }

    @Override
    public Book update(BookDto book) {
        Book b = getBook(book);
        b.setId(book.getId());
        return bookRepository.save(b);
    }



    @Override
    public void remove(BookDto book) {
        Book b = getBook(book);
        bookRepository.delete(b);
    }

    @Override
    public void remove(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void removeAll() {
        bookRepository.deleteAll();
    }

    private Book getBook(BookDto book) {
        return new Book(book.getTitle(), Integer.parseInt(book.getQuantity()), Double.parseDouble(book.getPrice()), book.getAuthor(), book.getGenre());
    }
}
