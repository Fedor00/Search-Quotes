package com.fedor.Quotes.CQRS.Command.FavoriteQuotesCommand;

import com.fedor.Quotes.Interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteFavoriteQuoteByIdCommand implements Request<Void> {
    private Long id;
}
