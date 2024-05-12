package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("SFG Publishing");
        publisher.setCity("St Petersburg");
        publisher.setState("FL");

        publisherRepository.save(publisher);

        Author parker = new Author("Parker", "NGO");
        Book book = new Book("Spring Boot", "091010");

        parker.getBooks().add(book);
        book.getAuthors().add(parker);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(parker);
        bookRepository.save(book);
        publisherRepository.save(publisher);

        Author john = new Author("John", "Jackson");
        Book book1 = new Book("Spring Framework", "788711");

        john.getBooks().add(book1);
        book1.getAuthors().add(john);
        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);

        authorRepository.save(john);
        bookRepository.save(book1);
        publisherRepository.save(publisher);

//        authorRepository.saveAll(List.of(parker, john));
//        bookRepository.saveAll(List.of(book, book1));

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher: " + publisherRepository.count() + "\nPublisher Number of Books: " + publisher.getBooks().size());
    }
}
