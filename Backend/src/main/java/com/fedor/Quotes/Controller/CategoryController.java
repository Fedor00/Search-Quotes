package com.fedor.Quotes.Controller;

import com.fedor.Quotes.CQRS.Query.CategoryQueries.FetchCategoriesByKeywordQuery;
import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final Mediator mediator;

    public CategoryController( Mediator mediator) {
        this.mediator = mediator;
    }

    @GetMapping("/all-filtered")
    public ResponseEntity<List<Category>> getCategoriesByKeyword(@RequestParam String keyword){
       List<Category> categories= mediator.send(new FetchCategoriesByKeywordQuery(keyword));
       if(!categories.isEmpty()) return ResponseEntity.ok(categories);
       else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
