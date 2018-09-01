package com.example.Dao.Mongo;

import com.example.Models.ItemSection.Weapon.Weapon;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import java.util.ArrayList;

public class WeaponDaoMongo {

    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;
    private Gson gson = new Gson();

    public WeaponDaoMongo() {
        this.mongoClient = new MongoClient( "localhost" , 27017 );
        this.db = mongoClient.getDB( "War-V1-DB" );
        DBCollection coll = db.getCollection("WeaponCollection");
        this.coll = db.getCollection("WeaponCollection");
    }

    public void addObject(Object object) {
        String weaponJSON = gson.toJson(object);
        BasicDBObject dbObject = (BasicDBObject) JSON.parse(weaponJSON);
        coll.insert(dbObject);
        System.out.println("Mission added successfully...");
    }

    public Weapon updateObject(String name, Object object) {
        String weaponJSON = gson.toJson(object);
        BasicDBObject updatedDBObject = (BasicDBObject) JSON.parse(weaponJSON);
        BasicDBObject query = new BasicDBObject("name", name);
        coll.update(query, updatedDBObject);
        return (Weapon) object;
    }

    public void removeObject(String name) {
        BasicDBObject query = new BasicDBObject("name", name);
        Cursor cursor;
        cursor = coll.find(query);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                coll.remove(dbObject);
            }
        } finally {
            cursor.close();
        }
        System.out.println("Mission deleted successfully...");
    }

    public Weapon getObject(Object object) {
        BasicDBObject query = new BasicDBObject("name", object);
        Weapon weapon = new Weapon();
        Cursor cursor;
        cursor = coll.find(query);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                weapon = gson.fromJson(dbObject.toString(), Weapon.class);
            }
        } finally {
            cursor.close();
        }
        return weapon;
    }


    public ArrayList<Weapon> getAll() {
        DBCursor cursor = coll.find();
        ArrayList<Weapon> weaponArrayList = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                Weapon weapon = gson.fromJson(dbObject.toString(), Weapon.class);
                weaponArrayList.add(weapon);
            }
        } finally {
            cursor.close();
        }
        return weaponArrayList;
    }

    public void removeAll() {
        DBCursor cursor = coll.find();
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                coll.remove(dbObject);
            }
        } finally {
            cursor.close();
        }
    }

    public void addMultiple() {
//        for (int i=0; i < 100; i++) {
//            coll.insert(new BasicDBObject("i", i));
//        }
    }

    public long collectionSize() {
        return coll.getCount();
    }

    public void droppingCollection() {
        coll.drop();
    }

}
