package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author parker = new Author("Parker", "NGO");
        Book book = new Book("Spring Boot", "091010");

        parker.getBooks().add(book);
        book.getAuthors().add(parker);

        Author john = new Author("John", "Jackson");
        Book book1 = new Book("Spring Framework", "788711");

        john.getBooks().add(book1);
        book1.getAuthors().add(john);

        authorRepository.saveAll(List.of(parker, john));
        bookRepository.saveAll(List.of(book, book1));

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
    }
}
