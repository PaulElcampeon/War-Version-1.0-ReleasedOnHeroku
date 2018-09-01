package com.example.Dao.Mongo;

import com.example.Models.OperationSection.Operation.Operation;
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.util.JSON;
import java.util.ArrayList;

public class OperationDaoMongo {

    private MongoClient mongoClient;
    private DB db;
    private DBCollection coll;
    private Gson gson = new Gson();

    public OperationDaoMongo() {
        this.mongoClient = new MongoClient( "localhost" , 27017 );
        this.db = mongoClient.getDB( "War-V1-DB" );
        DBCollection coll = db.getCollection("OperationCollection");
        this.coll = db.getCollection("OperationCollection");
    }

    public void addObject(Object object) {
        String operationJSON = gson.toJson(object);
        BasicDBObject dbObject = (BasicDBObject) JSON.parse(operationJSON);
        coll.insert(dbObject);
        System.out.println("Mission added successfully...");
    }

    public Operation updateObject(String name, Object object) {
        String operationJSON = gson.toJson(object);
        BasicDBObject updatedDBObject = (BasicDBObject) JSON.parse(operationJSON);
        BasicDBObject query = new BasicDBObject("name", name);
        coll.update(query, updatedDBObject);
        return (Operation) object;
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

    public Operation getObject(Object object) {
        BasicDBObject query = new BasicDBObject("name", object);
        Operation mission = new Operation();
        Cursor cursor;
        cursor = coll.find(query);
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                mission = gson.fromJson(dbObject.toString(), Operation.class);
            }
        } finally {
            cursor.close();
        }
        return mission;
    }


    public ArrayList<Operation> getAll() {
        DBCursor cursor = coll.find();
        ArrayList<Operation> operationArrayList = new ArrayList<>();
        try {
            while(cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                Operation warrior = gson.fromJson(dbObject.toString(), Operation.class);
                operationArrayList.add(warrior);
            }
        } finally {
            cursor.close();
        }
        return operationArrayList;
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
