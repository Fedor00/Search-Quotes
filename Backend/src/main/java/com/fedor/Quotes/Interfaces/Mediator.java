package com.fedor.Quotes.Interfaces;



public interface Mediator {
    <T> T send(final Request<T> request) ;
}
