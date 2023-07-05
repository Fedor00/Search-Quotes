package com.fedor.Quotes.CQRS.Query.QuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Quote;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class FetchRandomQuoteQuery implements Request<Optional<Quote>> {

}
