package bookStore.Mapper;

import bookStore.dto.BookDto;
import bookStore.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toBook(BookDto book) {
        return new Book(book.getTitle(), Integer.parseInt(book.getQuantity()), Double.parseDouble(book.getPrice()), book.getAuthor(), book.getGenre());
    }

    public BookDto toBookDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), String.valueOf(book.getQuantity()), String.valueOf(book.getPrice()));
    }
}
