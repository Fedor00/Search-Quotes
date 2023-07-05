package com.fedor.Quotes.Controller;

import com.fedor.Quotes.CQRS.Auth.FacebookLoginCommand;
import com.fedor.Quotes.CQRS.Auth.GoogleLoginCommand;
import com.fedor.Quotes.CQRS.Command.UserCommands.CreateUserCommand;
import com.fedor.Quotes.CQRS.Query.UserQueries.LoginUserQuery;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private Mediator mediator;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginUserQuery loginUserQuery) {
        return ResponseEntity.ok(mediator.send(loginUserQuery));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody CreateUserCommand createUserCommand) {
        return ResponseEntity.ok(mediator.send(createUserCommand));
    }

    @PostMapping("/login/{provider}")
    public ResponseEntity<String> socialLogin(@PathVariable String provider, @RequestBody String accessToken) {
        if(provider.equals("google"))
            return ResponseEntity.ok(mediator.send(new GoogleLoginCommand(accessToken)));
        else if (provider.equals("facebook"))
            return ResponseEntity.ok(mediator.send(new FacebookLoginCommand(accessToken)));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong provider.");
    }
}
