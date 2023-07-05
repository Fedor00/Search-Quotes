package com.fedor.Quotes.CQRS.CommandHandler.QuoteCommandHandler;

import com.fedor.Quotes.CQRS.Command.QuoteCommands.DeleteQuoteCommand;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Repository.QuoteRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteQuoteCommandHandler implements RequestHandler<DeleteQuoteCommand,Void> {
    private final QuoteRepository quoteRepository;

    public DeleteQuoteCommandHandler(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Void handle(DeleteQuoteCommand request) {
        quoteRepository.deleteById(request.getId());
        return null;
    }


}
