package com.fedor.Quotes.CQRS.CommandHandler.UserCommandHandler;

import com.fedor.Quotes.CQRS.Command.UserCommands.CreateUserCommand;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.User;
import com.fedor.Quotes.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler implements RequestHandler<CreateUserCommand, User> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserCommandHandler(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User handle(CreateUserCommand request) {
        System.out.println(request.getName() + " " +request.getEmail());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user=new User(request.getName(), request.getEmail());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
}
