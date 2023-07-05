package com.fedor.Quotes.CQRS.Query.QuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.QuoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class FetchQuotesByAuthorQuery implements Request<QuoteResponse> {
    private String keywords;
    private Integer pageNr,pageSize;
}
