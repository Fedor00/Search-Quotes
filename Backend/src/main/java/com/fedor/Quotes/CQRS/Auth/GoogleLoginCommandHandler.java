package com.fedor.Quotes.CQRS.Auth;

import com.fedor.Quotes.CQRS.Command.UserCommands.CreateSocialUserCommand;
import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByEmailAndProviderQuery;
import com.fedor.Quotes.Exception.UnauthorizedException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.JwtUtil;
import com.fedor.Quotes.Model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class GoogleLoginCommandHandler implements RequestHandler<GoogleLoginCommand, String> {
    final
    Mediator mediator;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public GoogleLoginCommandHandler(Mediator mediator) {
        this.mediator = mediator;
    }
    @Autowired
    protected JwtUtil jwtUtil;
    @Override
    public String handle(GoogleLoginCommand request) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY)
                    .setAudience(Collections.singletonList("838631330699-jidlog0ed7oteglrjf2pk20m5nvqj6c8.apps.googleusercontent.com"))
                    .build();
            GoogleIdToken idToken = verifier.verify(request.getAccessToken());
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                User alreadyExists=mediator.send(new FetchUserByEmailAndProviderQuery(email,"google"));
                if(alreadyExists==null) {
                    User user=mediator.send(new CreateSocialUserCommand((String) payload.get("name"), email, "google"));
                    return "Bearer " +jwtUtil.generateToken(user);
                }
                return "Bearer " +jwtUtil.generateToken(alreadyExists);
            } else {
                throw new UnauthorizedException("Invalid access token");
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
