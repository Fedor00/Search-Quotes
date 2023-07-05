package com.fedor.Quotes.CQRS.Command.UserCommands;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCommand implements Request<User> {
    private String name;
    private String email;
    private String password;

}
