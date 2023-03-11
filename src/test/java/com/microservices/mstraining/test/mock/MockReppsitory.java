package com.microservices.mstraining.test.mock;

import com.microservices.mstraining.test.fake.book.Book;
import com.microservices.mstraining.test.fake.book.BookRepository;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MockReppsitory implements BookRepository {

    int timesCalled=0;
    Book recentlyCalledWith=null;
    ArrayList<Book> books = new ArrayList();

    @Override
    public List<Book> findAll() {
        return null;
    }

    // Verify method being called
    // Verify method was called with the right object
    @Override
    public void save(Book book) {
        timesCalled++;
        recentlyCalledWith=book;
        books.add(book);
    }

    @Override
    public List<Book> findOlderBooks(LocalDate date) {
        return null;
    }
}
