package com.fedor.Quotes.Controller;

import com.fedor.Quotes.CQRS.Command.FavoriteQuotesCommand.CreateFavoriteQuoteCommand;
import com.fedor.Quotes.CQRS.Command.FavoriteQuotesCommand.DeleteFavoriteQuoteByIdCommand;
import com.fedor.Quotes.CQRS.Query.FavoriteQuoteQueries.FetchFavoriteQuotesQuery;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.FavoriteQuoteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/favorite-quotes")
public class FavoriteQuoteController {
    private final Mediator mediator;

    public FavoriteQuoteController(Mediator mediator) {
        this.mediator = mediator;
    }


    @PostMapping("/add/{id}")
    public ResponseEntity<?> addFavoriteQuote(
            @PathVariable Long id,
            @RequestHeader(value="Authorization") String jwt) {
        try {
            FavoriteQuote favoriteQuote=mediator.send(new CreateFavoriteQuoteCommand(jwt, id));
            return ResponseEntity.ok(favoriteQuote);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong id or jwt.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFavoriteQuote(@PathVariable Long id){
        return ResponseEntity.ok(mediator.send(new DeleteFavoriteQuoteByIdCommand(id)));
    }
    @GetMapping("/favorite-quotes")
    public ResponseEntity<?> getFavoriteQuotes(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestHeader(value="Authorization") String token) throws NotFoundException {
        FavoriteQuoteResponse favoriteQuoteResponse=mediator.send(new FetchFavoriteQuotesQuery(pageNo,pageSize,token));
        if(favoriteQuoteResponse!=null && !favoriteQuoteResponse.getQuotes().isEmpty()){
            return ResponseEntity.ok( favoriteQuoteResponse);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad token or no quotes.");

    }

}
