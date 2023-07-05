package com.fedor.Quotes.CQRS.Query.FavoriteQuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchPageFavoriteQuotesByUserQuery implements Request<Page<FavoriteQuote>> {
    private User user;
    private Pageable pageable;
}
