package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.CountQuotesQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountQuotesQueryHandler implements RequestHandler<CountQuotesQuery, Long> {
    @Autowired
    private QuoteRepository quoteRepository;
    @Override
    public Long handle(CountQuotesQuery request) {
        return quoteRepository.countQuotes();
    }
}
