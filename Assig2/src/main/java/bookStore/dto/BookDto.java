package bookStore.dto;

import bookStore.entity.Author;
import bookStore.entity.Genre;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookDto {
    private int id;

    @NotNull(message = "Title cannot be null.")
    @Size(min=1, message = "Title cannot be empty.")
    private String title;

    private Author author;
    private Genre genre;

    @Pattern(regexp = "^[0-9]+$", message = "Quantity must be a natural number.")
    private String quantity;

    @Pattern(regexp = "^[0-9]+\\.?[0-9]*$", message = "The price should be a positive real number.")
    private String price;

    public BookDto() {
    }

    public BookDto(int id, @NotNull(message = "Title cannot be null.") @Size(min = 1, message = "Title cannot be empty.") String title, Author author,
                   Genre genre, @Pattern(regexp = "^[0-9]+$" , message = "Quantity must be a natural number.") String quantity,
                   @Pattern(regexp = "^[0-9]+\\.?[0-9]*$", message = "The price should be a positive real number.") String price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
