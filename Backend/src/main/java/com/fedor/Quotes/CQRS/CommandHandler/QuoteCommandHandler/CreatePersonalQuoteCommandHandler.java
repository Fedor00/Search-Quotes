package com.fedor.Quotes.CQRS.CommandHandler.QuoteCommandHandler;

import com.fedor.Quotes.CQRS.Command.QuoteCommands.CreateNormalQuoteCommand;
import com.fedor.Quotes.CQRS.Command.QuoteCommands.CreatePersonalQuoteCommand;
import com.fedor.Quotes.CQRS.Query.UserQueries.GetUserFromTokenQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.User;
import org.springframework.stereotype.Component;



@Component
public class CreatePersonalQuoteCommandHandler implements RequestHandler<CreatePersonalQuoteCommand, Quote> {

    private final Mediator mediator;

    public CreatePersonalQuoteCommandHandler(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public Quote handle(CreatePersonalQuoteCommand request) {
        User user = mediator.send(new GetUserFromTokenQuery(request.getToken())).orElseThrow(() -> new NotFoundException("User not found"));
        request.getQuote().setUser(user);
        return mediator.send(new CreateNormalQuoteCommand(request.getQuote()));
    }
}
