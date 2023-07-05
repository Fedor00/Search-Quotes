package com.fedor.Quotes.Repository;

import com.fedor.Quotes.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Author> {
    Author findByName(String name);
}
