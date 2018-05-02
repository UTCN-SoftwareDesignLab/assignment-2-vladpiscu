package bookStore.service;

import bookStore.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookStore.repository.AuthorRepository;

import java.util.List;

public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(String author) {
        return authorRepository.save(new Author(author));
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }
}
