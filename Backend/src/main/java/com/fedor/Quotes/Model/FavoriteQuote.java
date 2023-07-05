package com.fedor.Quotes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorite_quotes")
public class FavoriteQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;



    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    public FavoriteQuote(User user, Quote quote) {
        this.user = user;
        this.quote = quote;
    }


}