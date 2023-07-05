package com.fedor.Quotes.CQRS.Query.QuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.QuoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FetchFilteredQuotesQuery implements Request<QuoteResponse> {
    private String keyword;
    private Integer pageNr,pageSize;
}
