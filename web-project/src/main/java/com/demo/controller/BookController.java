package com.demo.controller;

import com.demo.model.Book;
import com.demo.service.BookService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Log
@RequestMapping("/demo/books")
public class BookController extends BaseController{

    @Autowired
    private BookService bookService;

    private final BookController MO = methodOn(BookController.class);

    @GetMapping("{bookId}")
    public Resource<Book> get(@PathVariable("bookId") String bookId){
        Book book = bookService.getBook(bookId);
        Resource resource = new Resource(book);
        resource.add(linkTo(MO.get(bookId))
                .withSelfRel());
        return resource;
    }

    @PostMapping("/api/book")
    public Resource<Book> createByJson(@RequestBody Book book) {
        book = bookService.createBook(book);
        Resource resource = new Resource(book);
        resource.add(linkTo(MO.createByJson(book)).withSelfRel());
        return resource;
    }

    @PostMapping("/cgi/book")
    public String createByForm(@Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        return book.getName();
    }
}
