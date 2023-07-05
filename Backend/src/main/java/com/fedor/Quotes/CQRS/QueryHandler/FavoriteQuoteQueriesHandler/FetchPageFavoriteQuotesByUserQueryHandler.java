package com.fedor.Quotes.CQRS.QueryHandler.FavoriteQuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.FavoriteQuoteQueries.FetchPageFavoriteQuotesByUserQuery;
import com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler.FetchPageQuotesByUserQueryHandler;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Repository.FavoriteQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FetchPageFavoriteQuotesByUserQueryHandler implements RequestHandler<FetchPageFavoriteQuotesByUserQuery, Page<FavoriteQuote>> {

    @Autowired
    private FavoriteQuoteRepository favoriteQuoteRepository;

    @Override
    public Page<FavoriteQuote> handle(FetchPageFavoriteQuotesByUserQuery request) {
        return favoriteQuoteRepository.findByUser(request.getUser(),request.getPageable());
    }
}
