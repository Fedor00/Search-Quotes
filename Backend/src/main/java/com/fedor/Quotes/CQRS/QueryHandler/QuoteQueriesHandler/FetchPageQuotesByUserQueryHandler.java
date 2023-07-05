package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPageQuotesByUserQuery;
import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FetchPageQuotesByUserQueryHandler implements RequestHandler<FetchPageQuotesByUserQuery, Page<Quote>> {

    private final QuoteRepository quoteRepository;

    public FetchPageQuotesByUserQueryHandler(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> handle(FetchPageQuotesByUserQuery request) {
        return quoteRepository.findByUser(request.getUser(),request.getPageable());
    }
}
