package com.fedor.Quotes.CQRS.CommandHandler.QuoteCommandHandler;

import com.fedor.Quotes.CQRS.Command.QuoteCommands.UpdateQuoteCommand;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuoteByIdQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Repository.QuoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateQuoteCommandHandler implements RequestHandler<UpdateQuoteCommand, Quote> {
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    Mediator mediator;
    @Override
    public Quote handle(UpdateQuoteCommand request) {
        Quote quote=mediator.send(new FetchQuoteByIdQuery(request.getQuote().getId())).orElseThrow(() -> new NotFoundException("Quote not found"));
        request.getQuote().setLikes(quote.getLikes());
        return quoteRepository.save(request.getQuote());
    }
}
