package com.rameysoft.streamline.main.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rameysoft.streamline.main.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    public Iterable<Book> findBooksByAuthor(@Param("author") String author);
}
