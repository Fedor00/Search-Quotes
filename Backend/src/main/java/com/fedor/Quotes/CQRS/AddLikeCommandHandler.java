package com.fedor.Quotes.CQRS;

import com.fedor.Quotes.CQRS.Command.QuoteCommands.UpdateQuoteCommand;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.FetchQuoteByIdQuery;
import com.fedor.Quotes.CQRS.Query.UserQueries.GetUserFromTokenQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Like;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.LikeRepository;
import org.springframework.stereotype.Component;

@Component
public class AddLikeCommandHandler implements RequestHandler<AddLikeCommand,Like>{
    private final LikeRepository likeRepository;

    private final Mediator mediator;

    public AddLikeCommandHandler(LikeRepository likeRepository, Mediator mediator) {
        this.likeRepository = likeRepository;
        this.mediator = mediator;
    }

    @Override
    public Like handle(AddLikeCommand request) {
        User user = mediator.send(new GetUserFromTokenQuery(request.getToken())).orElseThrow(() -> new NotFoundException("User not found"));
        Quote quote= mediator.send(new FetchQuoteByIdQuery(request.getQuoteId())).orElseThrow(() -> new NotFoundException("User not found"));
        if (quote.getLikes().stream().anyMatch(like -> like.getUser().equals(user))) {
            return null;
        }
        quote.setLikeCount(quote.getLikeCount()+1);
        mediator.send(new UpdateQuoteCommand(quote));
        Like like=new Like(user,quote);
        return likeRepository.save(like);

    }
}
