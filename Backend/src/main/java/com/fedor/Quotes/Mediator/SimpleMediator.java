package com.fedor.Quotes.Mediator;

import com.fedor.Quotes.Interfaces.Mediator;
import com.fedor.Quotes.Interfaces.Request;
import com.fedor.Quotes.Interfaces.RequestHandler;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleMediator implements Mediator {

    private final ListableBeanFactory beanFactory;

    public SimpleMediator(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T send(Request<T> request)  {
        Class<?> requestClass = request.getClass();
        RequestHandler<Request<T>, T> handler = BeanFactoryUtils
                .beansOfTypeIncludingAncestors(beanFactory, RequestHandler.class)
                .values()
                .stream()
                .filter(h -> h.getClass().getGenericInterfaces()[0].getTypeName().contains(requestClass.getName()))
                .map(h -> (RequestHandler<Request<T>, T>) h)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No handler found for " + requestClass.getName()));
        return handler.handle(request);
    }
}
