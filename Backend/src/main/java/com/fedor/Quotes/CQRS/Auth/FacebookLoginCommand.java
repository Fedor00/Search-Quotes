package com.fedor.Quotes.CQRS.Auth;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacebookLoginCommand implements Request<String> {
    private String accessToken;
}
