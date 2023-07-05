package com.fedor.Quotes.CQRS.Command.FavoriteQuotesCommand;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.FavoriteQuote;
import com.fedor.Quotes.Model.Quote;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFavoriteQuoteCommand implements Request<FavoriteQuote> {
    private String token;
    private Long id;
}
