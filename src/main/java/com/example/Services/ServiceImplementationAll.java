package com.example.Services;

import java.util.Collection;

public interface ServiceImplementationAll {

    <E> Object getObject(E property) ;

    <E> void addObject(E object);

    void removeObject(String name);

    <E> Object updateObject(String name, E object);

    Collection<?> getAll();

    void removeAll();

    Collection<?> getAllExcept(String name);


}
