package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPageQuotesQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FetchPageQuotesQueryHandler implements RequestHandler<FetchPageQuotesQuery, Page<Quote>> {

    private final QuoteRepository quoteRepository;

    public FetchPageQuotesQueryHandler(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> handle(FetchPageQuotesQuery request) {
        return quoteRepository.findByDescriptionContainingIgnoreCase(request.getKeyword(),request.getPageable());
    }
}
