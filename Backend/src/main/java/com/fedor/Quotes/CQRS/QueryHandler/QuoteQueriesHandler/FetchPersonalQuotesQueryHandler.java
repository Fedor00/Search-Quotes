package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPageQuotesByUserQuery;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchPersonalQuotesQuery;
import com.fedor.Quotes.CQRS.Query.UserQueries.GetUserFromTokenQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.QuoteResponse;
import com.fedor.Quotes.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class FetchPersonalQuotesQueryHandler implements RequestHandler<FetchPersonalQuotesQuery,QuoteResponse> {
    private final Mediator mediator;

    public FetchPersonalQuotesQueryHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public QuoteResponse handle(FetchPersonalQuotesQuery request) {
        User user = mediator.send(new GetUserFromTokenQuery(request.getToken())).orElseThrow(() -> new NotFoundException("User not found"));

        Pageable paging = PageRequest.of(request.getPageNr() -1 , request.getPageSize());
        Page<Quote> pagedResult = mediator.send(new FetchPageQuotesByUserQuery(user,paging));
        if(pagedResult.hasContent()) {
            return new QuoteResponse(pagedResult.getContent(), pagedResult.getTotalPages());
        } else {
            return new QuoteResponse(new ArrayList<>(), 0);
        }
    }
}
