package com.microservices.mstraining.test.fake.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder=true)
public class Book {
    private int bookId;
    private double price;
    private String name;
    private LocalDate publishedDate;
}
