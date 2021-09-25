package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher pub = new Publisher("Mega", "4 killo", "Addis Ababa", "Addis Ababa", "001");
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        publisherRepository.save(pub);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(pub);
        pub.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pub);

        Author rog = new Author("Rog", "Williams");
        Book noEJB = new Book("No More EJB", "123456");

        rog.getBooks().add(noEJB);
        noEJB.getAuthors().add(rog);

        noEJB.setPublisher(pub);
        pub.getBooks().add(noEJB);

        authorRepository.save(rog);
        bookRepository.save(noEJB);
        publisherRepository.save(pub);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books " + bookRepository.count());
        System.out.println("Number of Publishers " + pub.getBooks().size());
    }
}
