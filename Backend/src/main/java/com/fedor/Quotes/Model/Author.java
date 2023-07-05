package com.fedor.Quotes.Model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "authors")

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters")
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Anonymous '")
    private String name;

    @NotNull
    @Size(min = 2, max = 255, message = "Bio must be between 2 and 255 characters")
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'UNKNOWN'")
    private String bio;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<Quote> quotes;


}