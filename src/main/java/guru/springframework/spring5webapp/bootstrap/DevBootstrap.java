package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author chetan = new Author("Chetan", "Bhagat");
        Publisher times = new Publisher("Times", "India");
        Book fivePoint = new Book("5 Point", times);
        chetan.getBooks().add(fivePoint);
        authorRepository.save(chetan);
        publisherRepository.save(times);
        bookRepository.save(fivePoint);

        Author rj = new Author("RJ", "Rowling");
        Publisher oxford = new Publisher("Oxford", "UK");
        Book harry = new Book("Harry Potter", oxford);
        rj.getBooks().add(harry);
        authorRepository.save(rj);
        publisherRepository.save(oxford);
        bookRepository.save(harry);
    }
}
