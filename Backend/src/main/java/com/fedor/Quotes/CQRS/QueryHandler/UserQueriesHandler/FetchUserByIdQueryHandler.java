package com.fedor.Quotes.CQRS.QueryHandler.UserQueriesHandler;

import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByIdQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class FetchUserByIdQueryHandler  implements RequestHandler<FetchUserByIdQuery, Optional<User>> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public Optional<User> handle(FetchUserByIdQuery request) {
        return userRepository.findById(request.getId());
    }
}
