package com.nagarro.libraryapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nagarro.libraryapi.model.Books;

@RepositoryRestResource(collectionResourceRel="books",path="books")
public interface BooksRepository extends JpaRepository<Books,Integer> {

}
