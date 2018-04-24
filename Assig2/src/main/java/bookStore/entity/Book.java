package bookStore.entity;

import javax.persistence.*;

/**
 * Created by Catalysts on 8/9/2015.
 */
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int quantity;
    private double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Genre genre;

    public Book() {
    }

    public Book(String title, int quantity, double price, Author author, Genre genre) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.author = author;
        this.genre = genre;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
