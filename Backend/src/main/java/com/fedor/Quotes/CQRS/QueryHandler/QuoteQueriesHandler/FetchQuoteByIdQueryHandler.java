package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuoteByIdQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FetchQuoteByIdQueryHandler implements RequestHandler<FetchQuoteByIdQuery, Optional<Quote>> {
    @Autowired
    private QuoteRepository quoteRepository;
    @Override
    public Optional<Quote> handle(FetchQuoteByIdQuery request) {
        return quoteRepository.findById(request.getId());
    }
}
