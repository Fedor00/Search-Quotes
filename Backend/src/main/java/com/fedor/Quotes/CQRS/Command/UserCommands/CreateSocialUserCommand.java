package com.fedor.Quotes.CQRS.Command.UserCommands;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateSocialUserCommand implements Request<User> {
    private String username;
    private String email;
    private String provider;
}
