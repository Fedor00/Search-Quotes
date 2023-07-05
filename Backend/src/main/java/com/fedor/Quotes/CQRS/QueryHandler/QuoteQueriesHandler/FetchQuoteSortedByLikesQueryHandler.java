package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuoteSortedByLikesQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.QuoteResponse;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class FetchQuoteSortedByLikesQueryHandler implements RequestHandler<FetchQuoteSortedByLikesQuery, QuoteResponse> {
    @Autowired
    private QuoteRepository quoteRepository;
    @Override
    public QuoteResponse handle(FetchQuoteSortedByLikesQuery request) {
        Pageable paging = PageRequest.of(request.getPageNr() -1 , request.getPageSize());
        Page<Quote> pagedResult=quoteRepository.findQuotesByOrderByLikeCountDesc(paging);
        if(pagedResult.hasContent()) {
            return new QuoteResponse(pagedResult.getContent(), pagedResult.getTotalPages());
        } else {
            return new QuoteResponse(new ArrayList<>(), 0);
        }

    }
}
