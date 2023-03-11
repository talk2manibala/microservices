package com.microservices.mstraining.test.fake;

import java.util.List;

public interface BookRepository {
    // get all the books by querying DB
    public List<Book> findAll();
    // save the book object into DB
    public void save(Book book);
}
