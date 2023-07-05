package com.fedor.Quotes.CQRS.Command.QuoteCommands;

import com.fedor.Quotes.Interfaces.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteQuoteCommand implements Request<Void> {
    private Long id;
}
