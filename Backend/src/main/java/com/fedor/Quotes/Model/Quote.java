
package com.fedor.Quotes.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "quotes")

public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    @Column

    private Long likeCount=0L;
    @ManyToOne
    private Author author;

    @NotNull(message = "Category must not be null")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "quote",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;


    @ManyToOne
    private User user;



}
