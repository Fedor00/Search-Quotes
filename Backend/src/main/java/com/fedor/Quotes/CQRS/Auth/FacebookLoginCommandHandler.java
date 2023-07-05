package com.fedor.Quotes.CQRS.Auth;

import com.facebook.ads.sdk.APIContext;
import com.fedor.Quotes.CQRS.Command.UserCommands.CreateSocialUserCommand;
import com.fedor.Quotes.CQRS.Query.UserQueries.FetchUserByEmailAndProviderQuery;
import com.fedor.Quotes.Exception.UnauthorizedException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.JwtUtil;
import com.fedor.Quotes.Model.User;
import org.springframework.stereotype.Component;

@Component
public class FacebookLoginCommandHandler implements RequestHandler<FacebookLoginCommand, String> {
    private final Mediator mediator;
    private final JwtUtil jwtUtil;

    public FacebookLoginCommandHandler(Mediator mediator, JwtUtil jwtUtil) {
        this.mediator = mediator;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String handle(FacebookLoginCommand request)  {
        try {
            APIContext apiContext = new APIContext(request.getAccessToken());

            com.facebook.ads.sdk.User user = new com.facebook.ads.sdk.User("me", apiContext).get().requestField("name").requestField("email").execute();
            if (user == null) {
                throw new UnauthorizedException("Invalid Facebook access token");
            }
            User alreadyExists=mediator.send(new FetchUserByEmailAndProviderQuery(user.getFieldEmail(),"facebook"));
            if(alreadyExists==null) {
                User newUser=mediator.send(new CreateSocialUserCommand(user.getFieldName(), user.getFieldEmail(), "facebook"));
                return "Bearer " +jwtUtil.generateToken(newUser);
            }
            return "Bearer " +jwtUtil.generateToken(alreadyExists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("An error occurred during Facebook login");

    }
}
