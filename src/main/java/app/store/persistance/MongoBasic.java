package app.store.persistance;

import app.store.util.ConfigReader;
import app.store.util.General;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoBasic {

    private MongoDB mongo = MongoDB.getInstance();
    private String collectionName;
    private MongoCollection mongoCollection;

    protected MongoBasic(MongoDB mongo, String collectionName) {
        this.collectionName = collectionName;
        this.mongo = mongo;
    }

    public String getTableName() {
        return this.collectionName;
    }


    public MongoDB getMongo() {
        return this.mongo;
    }


    public MongoCollection<Document> getMongoCollection() {
        if (this.mongoCollection == null) {
            this.mongoCollection = this.mongo.getCollection(this.collectionName);
        }
        return this.mongoCollection;
    }

    public void insertOne(Document document) {
        this.getMongoCollection().insertOne(document);
    }

    public DeleteResult deleteOnePhysically(Document document) {
        return this.getMongoCollection().deleteOne(document);
    }

    public UpdateResult updateOne(Bson olddocument, Bson newdocument) {
        return this.getMongoCollection().updateOne(olddocument, newdocument);
    }

    public void createCollection(String collectionNameToCreate) {
        this.mongo.getMongoDatabase().createCollection(collectionNameToCreate);
    }

    public void dropCollection(String collectionNameToDrop) {
        this.mongo.getMongoDatabase().getCollection(collectionNameToDrop).drop();
    }

    public List<Document> find(Bson find, Bson projection, Integer skip, Integer limit, Bson sort) {
        FindIterable<Document> list = this.getMongoCollection().find(find);
        if (projection != null) {
            list = list.projection(projection);
        }

        if (sort != null) {
            list = list.sort(sort);
        }

        if (skip != null) {
            list = list.skip(skip);
        }

        if (limit != null) {
            list = list.limit(limit);
        }

        return (List) list.into(new ArrayList());
    }

    public List<Document> find(Document find, Document projection, Integer skip, Integer limit, Document sort) {
        FindIterable<Document> list = this.getMongoCollection().find(find);
        if (projection != null) {
            list = list.projection(projection);
        }

        if (sort != null) {
            list = list.sort(sort);
        }

        if (skip != null) {
            list = list.skip(skip);
        }

        if (limit != null) {
            list = list.limit(limit);
        }

        return (List) list.into(new ArrayList());
    }

}
