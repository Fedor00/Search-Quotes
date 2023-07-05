package com.fedor.Quotes.CQRS.Query.CategoryQueries;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchCategoriesByKeywordQuery implements Request<List<Category>> {
    private String keyword;
}
