package com.fedor.Quotes.Interfaces;


public interface RequestHandler<TRequest extends Request<TResponse>, TResponse> {
    TResponse handle(TRequest request) ;
}
