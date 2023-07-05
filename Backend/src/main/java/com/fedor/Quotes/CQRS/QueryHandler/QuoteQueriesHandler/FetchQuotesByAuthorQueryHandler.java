package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPageQuotesByAuthorQuery;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuotesByAuthorQuery;
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
public class FetchQuotesByAuthorQueryHandler implements RequestHandler<FetchQuotesByAuthorQuery, QuoteResponse> {
    private final Mediator mediator;

    public FetchQuotesByAuthorQueryHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public QuoteResponse handle(FetchQuotesByAuthorQuery request) {
        Pageable paging = PageRequest.of(request.getPageNr() -1 , request.getPageSize());
        Page<Quote> pagedResult = mediator.send(new FetchPageQuotesByAuthorQuery(request.getKeywords(), paging));
        if(pagedResult.hasContent()) {
            return new QuoteResponse(pagedResult.getContent(), pagedResult.getTotalPages());
        } else {
            return new QuoteResponse(new ArrayList<>(), 0);
        }
    }
}
