package com.fedor.Quotes.CQRS.QueryHandler.UserQueriesHandler;

import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByIdQuery;
import com.fedor.Quotes.CQRS.Query.UserQueries.GetUserFromTokenQuery;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.JwtUtil;
import com.fedor.Quotes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class GetUserFromTokenQueryHandler implements RequestHandler<GetUserFromTokenQuery, Optional<User>> {
    private final JwtUtil jwtUtil;

    private final Mediator mediator;

    public GetUserFromTokenQueryHandler(JwtUtil jwtUtil, Mediator mediator) {
        this.jwtUtil = jwtUtil;
        this.mediator = mediator;
    }

    @Override
    public Optional<User> handle(GetUserFromTokenQuery request) {
        Long id=jwtUtil.getUserIdFromToken(request.getToken().substring(7));
        return mediator.send(new FetchUserByIdQuery(id));
    }
}
