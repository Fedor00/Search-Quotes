package com.fedor.Quotes.CQRS.Query.QuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Quote;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class FetchPageQuotesQuery implements Request<Page<Quote>> {
    private String keyword;
    private Pageable pageable;
}
