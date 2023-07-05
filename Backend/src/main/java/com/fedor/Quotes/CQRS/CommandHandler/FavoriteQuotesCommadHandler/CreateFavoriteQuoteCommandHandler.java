package com.fedor.Quotes.CQRS.CommandHandler.FavoriteQuotesCommadHandler;

import com.fedor.Quotes.CQRS.Command.FavoriteQuotesCommand.CreateFavoriteQuoteCommand;
import com.fedor.Quotes.CQRS.Command.QuoteCommands.CreateNormalQuoteCommand;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuoteByIdQuery;
import com.fedor.Quotes.CQRS.Query.UserQueries.GetUserFromTokenQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.FavoriteQuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateFavoriteQuoteCommandHandler implements RequestHandler<CreateFavoriteQuoteCommand, FavoriteQuote> {
    @Autowired
    private FavoriteQuoteRepository favoriteQuoteRepository;
    @Autowired
    private Mediator mediator;
    @Override
    public FavoriteQuote handle(CreateFavoriteQuoteCommand request) {
        User user = mediator.send(new GetUserFromTokenQuery(request.getToken())).orElseThrow(() -> new NotFoundException("User not found"));
        Quote quote=mediator.send(new FetchQuoteByIdQuery(request.getId())).orElseThrow(() -> new NotFoundException("Quote not found"));;
        return favoriteQuoteRepository.save(new FavoriteQuote(user,quote));
    }
}
