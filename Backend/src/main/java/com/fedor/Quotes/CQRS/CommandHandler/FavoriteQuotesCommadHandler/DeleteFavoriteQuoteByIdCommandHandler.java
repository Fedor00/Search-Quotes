package com.fedor.Quotes.CQRS.CommandHandler.FavoriteQuotesCommadHandler;

import com.fedor.Quotes.CQRS.Command.FavoriteQuotesCommand.DeleteFavoriteQuoteByIdCommand;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Repository.FavoriteQuoteRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteFavoriteQuoteByIdCommandHandler implements RequestHandler<DeleteFavoriteQuoteByIdCommand, Void>{
    private final FavoriteQuoteRepository favoriteQuoteRepository;

    public DeleteFavoriteQuoteByIdCommandHandler(FavoriteQuoteRepository favoriteQuoteRepository) {
        this.favoriteQuoteRepository = favoriteQuoteRepository;
    }


    @Override
    public Void handle(DeleteFavoriteQuoteByIdCommand request) {
        favoriteQuoteRepository.deleteById(request.getId());
        return null;
    }
}
