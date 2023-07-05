package com.fedor.Quotes.CQRS.QueryHandler.UserQueriesHandler;

import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByEmailAndProviderQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class FetchUserByEmailAndProviderQueryHandler implements RequestHandler<FetchUserByEmailAndProviderQuery, User> {
    private final UserRepository userRepository;

    public FetchUserByEmailAndProviderQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(FetchUserByEmailAndProviderQuery request) {
        return userRepository.findByEmailAndProvider(request.getEmail(), request.getProvider());
    }
}
