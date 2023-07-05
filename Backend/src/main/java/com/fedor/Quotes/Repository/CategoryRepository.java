package com.fedor.Quotes.Repository;


import com.fedor.Quotes.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository< Category,Long> {
    Category findCategoryByName(String name);
    List<Category> findByNameContainsIgnoreCase(String prefix);
}
