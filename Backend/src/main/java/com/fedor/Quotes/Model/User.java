package com.fedor.Quotes.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    @Column(nullable = false)
    private String email;

    private String password;
    private String provider;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value="user-quote")
    @JsonIgnore
    private List<Quote> quotes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference(value="user-favorite")
    @JsonIgnore
    private List<FavoriteQuote> favoriteQuotes;



    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}



