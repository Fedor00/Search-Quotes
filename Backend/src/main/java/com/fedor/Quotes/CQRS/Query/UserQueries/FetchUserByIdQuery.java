package com.fedor.Quotes.CQRS.Query.UserQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchUserByIdQuery implements Request<Optional<User>> {
    private Long id;
}
