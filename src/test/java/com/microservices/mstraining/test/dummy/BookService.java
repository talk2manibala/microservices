package com.microservices.mstraining.test.dummy;

import com.microservices.mstraining.test.fake.book.Book;
import com.microservices.mstraining.test.fake.book.BookRepository;
import jakarta.validation.constraints.Email;

import java.util.List;

public class BookService {

    private BookRepository bookRepository;
    private EmailService emailService;

    public BookService(BookRepository bookRepository, EmailService emailService) {
        this.bookRepository = bookRepository;
        this.emailService = emailService;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}