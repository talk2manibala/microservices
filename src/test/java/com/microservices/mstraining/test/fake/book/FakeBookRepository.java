package com.microservices.mstraining.test.fake.book;

import java.util.ArrayList;
import java.util.List;

public class FakeBookRepository implements BookRepository {

    ArrayList<Book> books = new ArrayList();

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }
}
