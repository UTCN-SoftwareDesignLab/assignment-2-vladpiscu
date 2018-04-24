package bookStore.service;

import bookStore.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre create(String genre);
    void removeAll();
}
