package com.fedor.Quotes.CQRS.QueryHandler.UserQueriesHandler;

import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByEmailQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetchUserByEmailQueryHandler implements RequestHandler<FetchUserByEmailQuery, User> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User handle(FetchUserByEmailQuery request) {
        return userRepository.findByEmail(request.getEmail());
    }
}
