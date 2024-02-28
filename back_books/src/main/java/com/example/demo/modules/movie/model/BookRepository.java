package com.example.demo.modules.movie.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository

public interface BookRepository extends JpaRepository<Book, Long>{
        Page<Book> findAll(Pageable pageable);

}
