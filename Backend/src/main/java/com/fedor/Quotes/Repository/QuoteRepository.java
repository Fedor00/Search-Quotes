package com.fedor.Quotes.Repository;

import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface QuoteRepository extends JpaRepository< Quote,Long> {
    @Query(value = "SELECT COUNT(*) FROM quotes", nativeQuery = true)
    long countQuotes();
    Page<Quote> findByUser(User user, Pageable pageable);
    Page<Quote> findByDescriptionContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Quote> findByAuthorNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Quote> findByUserNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(String userName,String authorName, Pageable pageable);
    Page<Quote> findAllByOrderByAuthorAsc(Pageable pageable);
    @Query("SELECT q FROM Quote q LEFT JOIN q.user u LEFT JOIN q.author a WHERE LOWER(COALESCE(u.name, '')) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(COALESCE(a.name, '')) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Quote> findByUserNameOrAuthorName(String keyword, Pageable pageable);

    @NotNull
    Page<Quote> findAll(@NotNull Pageable pageable);
    Page<Quote> findQuotesByOrderByLikeCountDesc(Pageable pageable);
}