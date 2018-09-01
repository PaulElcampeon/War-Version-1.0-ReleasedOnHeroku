package com.example.Services.DaoServices;

import com.example.Dao.Mongo.OperationDaoMongo;
import com.example.Services.ServiceImplementationAll;

import java.util.Collection;

public class OperationDaoServiceImplementation implements ServiceImplementationAll {

    private static OperationDaoMongo operationDaoMongo = new OperationDaoMongo();

    @Override
    public <E> Object getObject(E property) {
        return operationDaoMongo.getObject(property);
    }

    @Override
    public <E> void addObject(E object) {
        operationDaoMongo.addObject(object);
    }

    @Override
    public void removeObject(String name) {
        operationDaoMongo.removeObject(name);
    }

    @Override
    public <E> Object updateObject(String name, E object) {
        return operationDaoMongo.updateObject(name, object);
    }

    @Override
    public Collection<?> getAll() {
        return operationDaoMongo.getAll();
    }

    @Override
    public void removeAll() {
        operationDaoMongo.removeAll();
    }

    @Override
    public  Collection<?> getAllExcept(String name) {
        return operationDaoMongo.getAll();
    }
}
