package com.fedor.Quotes.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "likes")
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User must not be null")
    @ManyToOne
    @JsonIgnore
    private User user;

    @NotNull(message = "Quote must not be null")
    @ManyToOne
    @JsonIgnore
    private Quote quote;

    public Like(User user, Quote quote) {
        this.user = user;
        this.quote = quote;
    }

    // ... other fields, getters and setters
}
