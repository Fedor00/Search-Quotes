package com.fedor.Quotes.CQRS.CommandHandler.QuoteCommandHandler;

import com.fedor.Quotes.CQRS.Command.QuoteCommands.CreateNormalQuoteCommand;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Repository.QuoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateNormalQuoteCommandHandler implements RequestHandler<CreateNormalQuoteCommand, Quote> {
    private final QuoteRepository quoteRepository;

    public CreateNormalQuoteCommandHandler(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Quote handle(CreateNormalQuoteCommand request) {
        return quoteRepository.save(request.getQuote());
    }
}
