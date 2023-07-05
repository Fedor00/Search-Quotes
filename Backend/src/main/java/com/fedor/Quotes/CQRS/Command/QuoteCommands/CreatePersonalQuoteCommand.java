package com.fedor.Quotes.CQRS.Command.QuoteCommands;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Quote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonalQuoteCommand implements Request<Quote> {
    private String token;
    private Quote quote;
}
