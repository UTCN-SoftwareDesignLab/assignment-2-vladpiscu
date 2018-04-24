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

    @Pattern(regexp = "^[0-9]+$")
    private String quantity;

    @Pattern(regexp = "^[0-9]+\\.?[0-9]*$")
    private String price;

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
