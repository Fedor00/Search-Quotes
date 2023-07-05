package com.fedor.Quotes.CQRS.Query.FavoriteQuoteQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.FavoriteQuoteResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchFavoriteQuotesQuery implements Request<FavoriteQuoteResponse> {
    private Integer pageNr,pageSize;
    private String token;
}
