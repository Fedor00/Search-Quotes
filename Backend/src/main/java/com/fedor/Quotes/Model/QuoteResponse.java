package com.fedor.Quotes.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class QuoteResponse {
    private List<Quote> quotes;
    private int totalPages;
}
