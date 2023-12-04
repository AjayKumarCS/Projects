package com.nagarro.libraryapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nagarro.libraryapi.model.Authors;

@RepositoryRestResource(collectionResourceRel="authors",path="authors")
public interface AuthorsRepository extends JpaRepository<Authors,Integer> {

}

