package com.example.SpringMongoDBInteractionWeb.mongo.resource;

import com.example.SpringMongoDBInteractionWeb.mongo.model.Book;
import com.example.SpringMongoDBInteractionWeb.mongo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookRepository repository;


    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book) {
        repository.save(book);
        return "Book Added with id"+book.getId();
    }

    @GetMapping("/findAllBooks")
    public List<Book> getBooks()
    {
        List<Book> books= repository.findAll();
        return books;
    }

    @GetMapping("/findBookOfId/{id}")
    public Optional<Book> getBookOfId(@PathVariable int id)
    {
        Optional<Book> book= repository.findById(id);
        return book;
    }

    @DeleteMapping("/deleteBookOfId/{id}")
    public String saveBook(@PathVariable int id) {
        repository.deleteById(id);
        return "Book Deleted with id" +id;
    }

    @GetMapping("/findBookBasedOnCriteria")
    public Optional<Book> getBookBasedOnCriteria(@RequestBody Book book)
    {
       String authorName= book.getAuthorName();
       String bookName=book.getBookName();
        Optional<Book> resultBook= repository.getBooksByAuthorAndName(authorName,bookName);
        return resultBook;
    }

    @GetMapping("/findBookBasedOnComplexCriteria")
    public List<Book> getBookBasedOnComplexCriteria(@RequestBody Book book)
    {
        String authorName= book.getAuthorName();
        String bookName=book.getBookName();
        int price=book.getPrice();
        List<Book> resultBook= repository.getBooksBySomeCriteria(authorName,bookName,price);
        return resultBook;
    }


    @GetMapping("/findBookBasedOnAuthorName/{authorName}")
    public Optional<Book> getBookByAuthorName(@PathVariable String authorName)
    {
        Optional<Book> book= repository.findByAuthorName(authorName);
        return book;
    }

}