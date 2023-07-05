package com.fedor.Quotes.CQRS.QueryHandler.FavoriteQuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.FavoriteQuoteQueries.FetchFavoriteQuotesQuery;
import com.fedor.Quotes.CQRS.Query.FavoriteQuoteQueries.FetchPageFavoriteQuotesByUserQuery;
import com.fedor.Quotes.CQRS.Query.UserQueries.GetUserFromTokenQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.FavoriteQuoteResponse;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FetchFavoriteQuotesQueryHandler implements RequestHandler<FetchFavoriteQuotesQuery, FavoriteQuoteResponse> {
    private final Mediator mediator;

    public FetchFavoriteQuotesQueryHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public FavoriteQuoteResponse handle(FetchFavoriteQuotesQuery request) {
        User user = mediator.send(new GetUserFromTokenQuery(request.getToken())).orElseThrow(() -> new NotFoundException("User not found"));

        Pageable paging = PageRequest.of(request.getPageNr() -1 , request.getPageSize());
        Page<FavoriteQuote> pagedResult = mediator.send(new FetchPageFavoriteQuotesByUserQuery(user,paging));
        if(pagedResult.hasContent()) {

            List<Quote> quotes = pagedResult.getContent().stream()
                    .map(FavoriteQuote::getQuote)
                    .toList();
            return new FavoriteQuoteResponse(quotes, pagedResult.getTotalPages());
        } else {
            return new FavoriteQuoteResponse(new ArrayList<>(), 0);
        }
    }
}
