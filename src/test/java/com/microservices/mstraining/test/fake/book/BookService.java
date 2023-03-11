package com.microservices.mstraining.test.fake.book;

import java.time.LocalDate;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> applyDiscountOnOlderBooks(LocalDate date, double percentage) {
        // find the books older than the given date
            List<Book> olderBooks = bookRepository.findOlderBooks(date);
        // apply discount and set the new price
        olderBooks.stream().forEach(x->x.setPrice(x.getPrice()-x.getPrice()*percentage/100));
        // return updated books list
        return olderBooks;
    }

}
