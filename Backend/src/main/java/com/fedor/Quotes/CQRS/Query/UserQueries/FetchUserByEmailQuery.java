package com.fedor.Quotes.CQRS.Query.UserQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FetchUserByEmailQuery implements Request<User> {
    private String email;
}
