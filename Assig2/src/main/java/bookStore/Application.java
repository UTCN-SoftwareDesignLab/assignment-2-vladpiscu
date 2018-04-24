package bookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import bookStore.repository.AuthorRepository;
import bookStore.repository.BookRepository;
import bookStore.repository.GenreRepository;
import bookStore.service.AuthorService;
import bookStore.service.AuthorServiceImpl;
import bookStore.service.BookService;
import bookStore.service.BookServiceImpl;

@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"bookStore.repository"})
@PropertySource(value = "classpath:application.properties")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "AuthorService")
    public AuthorService authorService(AuthorRepository repository) {
        return new AuthorServiceImpl(repository);
    }
}