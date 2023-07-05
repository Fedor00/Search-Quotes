package com.fedor.Quotes.Repository;


import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteQuoteRepository extends JpaRepository<FavoriteQuote,Long > {
    Page<FavoriteQuote> findByUser(User user, Pageable pageable);

}
