package com.fedor.Quotes.CQRS.QueryHandler.QuoteQueriesHandler;

import com.fedor.Quotes.CQRS.Query.QuoteQueries.CountQuotesQuery;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuoteByIdQuery;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchRandomQuoteQuery;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Component
public class FetchRandomQuoteQueryHandler implements RequestHandler<FetchRandomQuoteQuery, Optional<Quote>> {
    private final Mediator mediator;

    public FetchRandomQuoteQueryHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Optional<Quote> handle(FetchRandomQuoteQuery request) {
        long totalQuotes = mediator.send(new CountQuotesQuery());
        if (totalQuotes == 0) {
            return Optional.empty();
        }
        long randomIndex = ThreadLocalRandom.current().nextLong(1, totalQuotes + 1);
        return mediator.send(new FetchQuoteByIdQuery(randomIndex));
    }
}
