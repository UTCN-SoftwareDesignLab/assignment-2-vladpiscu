package bookStore.service;

import bookStore.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author create(String author);
    void deleteAll();
}
