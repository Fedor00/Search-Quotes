package com.fedor.Quotes.CQRS.CommandHandler.UserCommandHandler;

import com.fedor.Quotes.CQRS.Command.UserCommands.CreateSocialUserCommand;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateSocialUserCommandHandler implements RequestHandler<CreateSocialUserCommand, User> {
    private final UserRepository userRepository;


    public CreateSocialUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User handle(CreateSocialUserCommand request) {
        User user = new User(request.getUsername(), request.getEmail());
        user.setProvider(request.getProvider());
        return userRepository.save(user);
    }
}