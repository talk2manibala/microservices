package com.microservices.mstraining.test.stub;

import com.microservices.mstraining.test.fake.book.Book;
import com.microservices.mstraining.test.fake.book.BookRepository;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StubRepository implements BookRepository {


    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public List<Book> findOlderBooks(LocalDate date) {
        ArrayList<Book> books = new ArrayList<>();
        Book book1 = Book.builder().bookId(1).price(200).name("book-1").publishedDate(LocalDate.now()).build();
        Book book2 = Book.builder().bookId(2).price(400).name("book-2").publishedDate(LocalDate.now().minusDays(10)).build();

        books.add(book1);
        books.add(book2);
        return books;
    }
}
