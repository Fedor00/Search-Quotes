package com.fedor.Quotes.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteQuoteResponse {
    private List<Quote> quotes;
    private int totalPages;
}
