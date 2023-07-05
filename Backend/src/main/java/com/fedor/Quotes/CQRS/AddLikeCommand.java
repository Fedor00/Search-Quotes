package com.fedor.Quotes.CQRS;

import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Model.Like;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddLikeCommand implements Request<Like> {
    private String token;
    private Long quoteId;
}
