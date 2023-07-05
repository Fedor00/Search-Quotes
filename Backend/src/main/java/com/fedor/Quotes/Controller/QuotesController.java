package com.fedor.Quotes.Controller;

import com.fedor.Quotes.CQRS.Command.QuoteCommands.CreateNormalQuoteCommand;
import com.fedor.Quotes.CQRS.Command.QuoteCommands.CreatePersonalQuoteCommand;
import com.fedor.Quotes.CQRS.Command.QuoteCommands.DeleteQuoteCommand;
import com.fedor.Quotes.CQRS.Command.QuoteCommands.UpdateQuoteCommand;
import com.fedor.Quotes.CQRS.Query.QuoteQueries.*;
import com.fedor.Quotes.Exception.NotFoundException;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.QuoteResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/quote")
public class QuotesController {

    private final Mediator mediator;

    public QuotesController( Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping("/add-personal-quote")
    public ResponseEntity<?> addPersonalQuote(@RequestHeader("Authorization") String jwt, @Valid  @RequestBody Quote quote) {
        try {
            Quote savedQuote = mediator.send(new CreatePersonalQuoteCommand(jwt,quote));
            return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/personal-quotes")
    public ResponseEntity<QuoteResponse> getPersonalQuotes(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestHeader(value="Authorization") String token) throws NotFoundException {
        return ResponseEntity.ok( mediator.send(new FetchPersonalQuotesQuery(pageNo,pageSize,token)));
    }
    @PostMapping("/add-author")
    public ResponseEntity<?> addAuthorQuote(@Valid @RequestBody Quote quote) {
        Quote savedQuote = mediator.send(new CreateNormalQuoteCommand(quote));
        return new ResponseEntity<>(savedQuote, HttpStatus.CREATED);
    }
    @PostMapping("/add-author-list")
    public ResponseEntity<?> addAuthorQuoteList(@Valid @RequestBody List<Quote> quote) {
        quote.forEach(quote1 ->mediator.send(new CreateNormalQuoteCommand(quote1)) );

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuote(@PathVariable Long id) {
        mediator.send(new DeleteQuoteCommand(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/daily")
    public ResponseEntity<?> getRandomQuote() {
        Optional<Quote> quote = mediator.send(new FetchRandomQuoteQuery());
        if (quote.isPresent()) {
            return new ResponseEntity<>(quote.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Quotes available");
        }
    }
    @PostMapping("/update")
    public ResponseEntity<Quote> updateQuote(@Valid @RequestBody Quote quote){
        return ResponseEntity.ok(mediator.send(new UpdateQuoteCommand(quote)));
    }
    @GetMapping("/pages-by-keywords")
    public ResponseEntity<QuoteResponse> getPageOfQuotesByKeywords(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam String keywords) throws NotFoundException {
        return ResponseEntity.ok( mediator.send(new FetchFilteredQuotesQuery(keywords,pageNo,pageSize)));
    }
    @GetMapping("/pages-by-author")
    public ResponseEntity<QuoteResponse> getPageOfQuotesByAuthor(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam String keywords) throws NotFoundException {
        System.out.println("yes"+keywords);
        return ResponseEntity.ok( mediator.send(new FetchQuotesByAuthorQuery(keywords,pageNo,pageSize)));
    }
    @GetMapping("/pages-by-author-ascending")
    public ResponseEntity<QuoteResponse> getQuotesByUser(@RequestParam(defaultValue = "0") int pageNo,
                                                         @RequestParam(defaultValue = "10") int pageSize) {
        System.out.println("i am here");
        return ResponseEntity.ok( mediator.send(new FetchQuoteSortedByLikesQuery(pageNo,pageSize)));
    }
}
