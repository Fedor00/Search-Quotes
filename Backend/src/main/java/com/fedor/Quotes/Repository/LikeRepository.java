package com.fedor.Quotes.Repository;

import com.fedor.Quotes.Model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
