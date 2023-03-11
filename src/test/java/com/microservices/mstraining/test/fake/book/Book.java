package com.microservices.mstraining.test.fake.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Book {
    private int bookId;
    private double price;
    private String name;
}
