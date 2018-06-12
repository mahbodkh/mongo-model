package app.store.persistance;


import app.store.util.ConfigReader;
import com.mongodb.client.*;

public class MongoDB {
    private static MongoDB instance = null;
    private MongoClient mongoClient;
    private MongoDatabase database;

    static {
        try {
            instance = new MongoDB();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }


    private MongoDB() {
        init();
    }

    private void init() {
        mongoClient = MongoClients.create(ConfigReader.getString("mongodb.host"));
        database = mongoClient.getDatabase(ConfigReader.getString("mongodb.database"));
        if (database == null) {
            //todo
        }

    }

    public static MongoDB getInstance() {
        if (instance == null) {
            synchronized (MongoDB.class) {
                if (instance == null) {
                    instance = new MongoDB();
                }
            }
        }
        return instance;
    }

    public MongoCollection getCollection(String collectionName) {
        return mongoClient.getDatabase(database.getName()).getCollection(collectionName);
    }

    public MongoDatabase getMongoDatabase() {
        return this.database;
    }

}
