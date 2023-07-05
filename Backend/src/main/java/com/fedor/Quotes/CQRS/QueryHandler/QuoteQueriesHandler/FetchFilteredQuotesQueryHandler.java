package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchFilteredQuotesQuery;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPageQuotesQuery;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.QuoteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class FetchFilteredQuotesQueryHandler implements RequestHandler<FetchFilteredQuotesQuery, QuoteResponse> {
    private final Mediator mediator;

    public FetchFilteredQuotesQueryHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public QuoteResponse handle(FetchFilteredQuotesQuery request) {
        Pageable paging = PageRequest.of(request.getPageNr() -1 , request.getPageSize());
        Page<Quote> pagedResult = mediator.send(new FetchPageQuotesQuery(request.getKeyword(),paging));
        if(pagedResult.hasContent()) {
            return new QuoteResponse(pagedResult.getContent(), pagedResult.getTotalPages());
        } else {
            return new QuoteResponse(new ArrayList<>(), 0);
        }
    }
}
