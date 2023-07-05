package com.fedor.Quotes.CQRS.Query.QuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Quote;
import com.fedor.Quotes.Model.QuoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchPersonalQuotesQuery implements Request<QuoteResponse> {
    private Integer pageNr,pageSize;
    private String token;
}
