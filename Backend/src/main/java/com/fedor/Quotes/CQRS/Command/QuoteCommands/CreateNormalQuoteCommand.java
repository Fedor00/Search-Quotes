package com.fedor.Quotes.CQRS.Command.QuoteCommands;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Quote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNormalQuoteCommand implements Request<Quote> {
    private Quote quote;
}
