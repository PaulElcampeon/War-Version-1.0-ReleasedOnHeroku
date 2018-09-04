package com.example.Services.DaoServices;

import com.example.Dao.Mongo.WarriorDaoV3Mongo;
import com.example.Services.ServiceImplementationAll;

import java.util.Collection;

public class WarriorDaoServiceImplementation implements ServiceImplementationAll {

    private static WarriorDaoV3Mongo warriorDaoV3Mongo = new WarriorDaoV3Mongo();


    @Override
    public <E> Object getObject(E property){
        return warriorDaoV3Mongo.getObject(property);
    }

    @Override
    public <E> void addObject(E object){
        warriorDaoV3Mongo.addObject(object);
    }

    @Override
    public void removeObject(String name) {
        warriorDaoV3Mongo.removeObject(name);
    }

    @Override
    public <E> Object updateObject(String name, E object) {
        return warriorDaoV3Mongo.updateObject(name, object);
    }

    @Override
    public Collection<?> getAll() {
        return warriorDaoV3Mongo.getAll();
    }

    @Override
    public void removeAll() {
        warriorDaoV3Mongo.removeAll();
    }

    @Override
    public  Collection<?> getAllExcept(String name) {
        return warriorDaoV3Mongo.getAllExcept(name);
    }

    public <E> Object getObjectWithCredentials(E property) {
        return warriorDaoV3Mongo.getObjectWithCredentials(property);
    }



}
