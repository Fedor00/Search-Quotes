package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPageQuotesByAuthorQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FetchPageQuotesByAuthorQueryHandler implements RequestHandler<FetchPageQuotesByAuthorQuery, Page<Quote>> {
    private final QuoteRepository quoteRepository;

    public FetchPageQuotesByAuthorQueryHandler(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Page<Quote> handle(FetchPageQuotesByAuthorQuery request) {
        return quoteRepository.findByUserNameOrAuthorName(request.getKeyword(), request.getPageable());
    }
}
