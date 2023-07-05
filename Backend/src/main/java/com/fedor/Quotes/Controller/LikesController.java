package com.fedor.Quotes.Controller;

import com.fedor.Quotes.CQRS.AddLikeCommand;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Model.Like;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/likes")
public class LikesController {

    private final Mediator mediator;

    public LikesController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/add/{quoteId}")
    public ResponseEntity<?> likeQuote(@PathVariable Long quoteId, @RequestHeader("Authorization") String jwt) {
        Like like=mediator.send(new AddLikeCommand(jwt,quoteId));
        if(like!=null)
            return ResponseEntity.ok(like);
        else
            return new ResponseEntity<>("Quote liked successfully", HttpStatus.NOT_ACCEPTABLE);
    }
}
