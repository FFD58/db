package ru.fafurin.project.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;

public class LogService implements Closeable {
    private final MongoClient mongoClient;
    private final MongoCollection<Document> logCollection;

    public LogService() {
        mongoClient = MongoClients.create();
        MongoDatabase database = mongoClient.getDatabase("users");
        logCollection = database.getCollection("logs");
    }

    public void add(Map<String, Object> data) {
        logCollection.insertOne(new Document(data));
    }

    public void printLogs() {
        logCollection.find().forEach((Consumer<Document>) System.out::println);
    }

    @Override
    public void close() throws IOException {
        mongoClient.close();
    }
}
