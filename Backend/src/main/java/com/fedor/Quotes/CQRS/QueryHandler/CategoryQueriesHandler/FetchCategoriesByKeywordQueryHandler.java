package com.fedor.Quotes.CQRS.QueryHandler.CategoryQueriesHandler;

import com.fedor.Quotes.CQRS.Query.CategoryQueries.FetchCategoriesByKeywordQuery;
import com.fedor.Quotes.Interfaces.RequestHandler;
import com.fedor.Quotes.Model.Category;
import com.fedor.Quotes.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FetchCategoriesByKeywordQueryHandler implements RequestHandler<FetchCategoriesByKeywordQuery, List<Category>> {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> handle(FetchCategoriesByKeywordQuery request) {

        return categoryRepository.findByNameContainsIgnoreCase(request.getKeyword());
    }
}
