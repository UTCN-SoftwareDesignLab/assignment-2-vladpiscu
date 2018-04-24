package bookStore.service;

import bookStore.entity.Genre;
import bookStore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre create(String genre) {
        return genreRepository.save(new Genre(genre));
    }

    @Override
    public void removeAll() {
        genreRepository.deleteAll();
    }
}
