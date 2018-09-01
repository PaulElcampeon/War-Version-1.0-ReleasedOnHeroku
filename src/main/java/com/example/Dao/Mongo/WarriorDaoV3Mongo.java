package com.example.Dao.Mongo;

import com.example.Comparator.WarriorComparator.WarriorComparator;
import com.example.Models.Warrior.Warrior;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import java.util.ArrayList;
import java.util.Collections;

public class WarriorDaoV3Mongo {

    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;
    private Gson gson = new Gson();

    public WarriorDaoV3Mongo() {
        this.mongoClient = new MongoClient( new MongoClientURI("mongodb://PaulOladele:Elcampeon3@ds141872.mlab.com:41872/war-v-1-0"));

//        this.mongoClient = new MongoClient( "ds141872.mlab.com", 41872);
        this.db = mongoClient.getDB( "war-v-1-0" );
        DBCollection coll = db.getCollection("WarriorCollection");
        this.coll = db.getCollection("WarriorCollection");
    }

    public void addObject(Object object) {
        String warriorJSON = gson.toJson(object);
        BasicDBObject dbObject = (BasicDBObject) JSON.parse(warriorJSON);
        coll.insert(dbObject);
        System.out.println("Warrior added successfully...");
    }

    public Warrior updateObject(String name, Object object) {
        String warriorJSON = gson.toJson(object);
        BasicDBObject updatedDBObject = (BasicDBObject) JSON.parse(warriorJSON);
        BasicDBObject query = new BasicDBObject("name", name);
        coll.update(query, updatedDBObject);
        return (Warrior) object;
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
        System.out.println("Warrior deleted successfully...");
    }

    public Warrior getObject(Object object) {
        BasicDBObject query = new BasicDBObject("name", object);
        Warrior warrior = new Warrior();
        Cursor cursor;
        cursor = coll.find(query);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                System.out.println(dbObject.toString());
                warrior = gson.fromJson(dbObject.toString(), Warrior.class);//problem
                System.out.println("WARRIOR FROM DB IS " + warrior);
            }
        } finally {
            cursor.close();
        }
        return warrior;
    }

    public Warrior getObjectWithCredentials(Object object) {
        BasicDBObject query = new BasicDBObject("name", object);
        Warrior warrior = new Warrior();
        Cursor cursor;
        cursor = coll.find(query);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                warrior = gson.fromJson(dbObject.toString(), Warrior.class);
            }
        } finally {
            cursor.close();
        }
        return warrior;
    }

    public ArrayList<Warrior> getAll() {
        DBCursor cursor = coll.find();
        ArrayList<Warrior> warriors = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                Warrior warrior = gson.fromJson(dbObject.toString(), Warrior.class);
                warriors.add(warrior);
            }
        } finally {
            cursor.close();
        }
        Collections.sort(warriors, new WarriorComparator());
        return warriors;
    }

    public ArrayList<Warrior> getAllExcept(String name) {
        DBCursor cursor = coll.find();
        ArrayList<Warrior> warriors = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                Warrior warrior = gson.fromJson(dbObject.toString(), Warrior.class);
                if (!warrior.getName().equalsIgnoreCase(name)) {
                    warriors.add(warrior);
                }
            }
        } finally {
            cursor.close();
        }
        return warriors;
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
