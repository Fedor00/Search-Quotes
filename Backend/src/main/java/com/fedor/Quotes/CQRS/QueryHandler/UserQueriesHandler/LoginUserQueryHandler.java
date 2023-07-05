package com.fedor.Quotes.CQRS.QueryHandler.UserQueriesHandler;

import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByEmailQuery;
import com.fedor.Quotes.CQRS.Query.UserQueries.LoginUserQuery;
import com.fedor.Quotes.Exception.UnauthorizedException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.JwtUtil;
import com.fedor.Quotes.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUserQueryHandler implements RequestHandler<LoginUserQuery,String> {
    private final Mediator mediator;
    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public LoginUserQueryHandler(Mediator mediator, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.mediator = mediator;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String handle(LoginUserQuery request) {
        User user = mediator.send(new FetchUserByEmailQuery(request.getEmail()));
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String jwtToken = jwtUtil.generateToken(user);
            return ("Bearer " + jwtToken);
        } else {
            throw new UnauthorizedException("Invalid credentials.");
        }
    }
}
