package com.fedor.Quotes.CQRS.Query.UserQueries;

import com.fedor.Quotes.Interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserQuery implements Request<String> {
    private String email;
    private String password;
}
