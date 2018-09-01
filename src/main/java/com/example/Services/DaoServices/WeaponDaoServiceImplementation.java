package com.example.Services.DaoServices;


import com.example.Dao.Mongo.WeaponDaoMongo;
import com.example.Services.ServiceImplementationAll;

import java.util.Collection;

public class WeaponDaoServiceImplementation implements ServiceImplementationAll {

    private static WeaponDaoMongo weaponDaoMongo = new WeaponDaoMongo();

    @Override
    public <E> Object getObject(E property) {
        return weaponDaoMongo.getObject(property);
    }

    @Override
    public <E> void addObject(E object) {
        weaponDaoMongo.addObject(object);
    }

    @Override
    public void removeObject(String name) {
        weaponDaoMongo.removeObject(name);
    }

    @Override
    public <E> Object updateObject(String name, E object) {
        return weaponDaoMongo.updateObject(name, object);
    }

    @Override
    public Collection<?> getAll() {
        return weaponDaoMongo.getAll();
    }

    @Override
    public void removeAll() {
        weaponDaoMongo.removeAll();
    }

    @Override
    public  Collection<?> getAllExcept(String name) {
        return weaponDaoMongo.getAll();
    }
}
