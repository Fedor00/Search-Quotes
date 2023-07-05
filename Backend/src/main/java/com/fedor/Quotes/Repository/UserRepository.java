package com.fedor.Quotes.Repository;

import com.fedor.Quotes.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByEmailAndPassword(String email,String password);
    User findByEmailAndProvider(String email,String provider);
}
