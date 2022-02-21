package com.example.SpringMongoDBInteractionWeb.mongo.repository;

import com.example.SpringMongoDBInteractionWeb.mongo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,Integer> {

    @Query("{authorName: ?0, bookName: ?1}")
    Optional<Book> getBooksByAuthorAndName(String authorName, String bookName);

    @Query("{price: {$gt:?2} , $or: [{authorName: ?0},    {bookName: ?1}]}")
    List<Book> getBooksBySomeCriteria(String authorName, String bookName, int price);
}
