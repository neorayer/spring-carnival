package com.demo.service;

import com.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookService {

    public Book getBook(String bookId) {
        Book book =new Book();
        book.setId(bookId);
        book.setName("Moby Dick");
        return book;
    }

    public Book createBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        return book;
    }
}
