package com.qrpackaging.backend.qrpackaging.dbservice;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.UpdateResult;
import com.qrpackaging.backend.qrpackaging.model.PackageList;
import com.qrpackaging.backend.qrpackaging.model.User;
import com.qrpackaging.backend.qrpackaging.model.UserInformation;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDBService {
    private String DATABASENAME = "qrpackaging";
    private ConnectionString connString = new ConnectionString(
            "mongodb://localhost:27017"
    );

    public MongoDBService(String databaseName) {
        this.DATABASENAME = databaseName;
    }

    public static void TEST() {
        PackageList pl1 = new PackageList("123456", "968431187", "210/97 Poseidon", "210/97 Ratchada");
        PackageList pl2 = new PackageList("123457", "968431187", "210/97 Poseidon", "210/97 Ratchada");
        PackageList pl3 = new PackageList("123458", "968431187", "210/97 Poseidon", "210/97 Ratchada");
        PackageList pl4 = new PackageList("123459", "968431187", "210/97 Poseidon", "210/97 Ratchada");

        ArrayList<PackageList> packageLists = new ArrayList<>();
        packageLists.add(pl1);
        packageLists.add(pl2);
        packageLists.add(pl3);
        packageLists.add(pl4);

        User user1 = new User("nunkung1", packageLists);

        ConnectionString connString = new ConnectionString(
                "mongodb://localhost:27017"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("qrpackaging");
            MongoCollection<Document> testCollection = database.getCollection("User");

            testCollection.insertOne(user1.toBsonDoc());

        }
        USERGENERATOR();
    }

    public static void USERGENERATOR() {
        UserInformation userInformation1 = new UserInformation("nunz9684", "123456789");
        UserInformation userInformation2 = new UserInformation("abc", "123456789");

        ConnectionString connString = new ConnectionString(
                "mongodb://localhost:27017"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("UserInformation");
            MongoCollection<Document> testCollection = database.getCollection("User");

            List<Document> doc = new ArrayList<>();
            doc.add(userInformation1.toBsonDoc());
            doc.add(userInformation2.toBsonDoc());

            testCollection.insertOne(new Document().append("User", doc));
        }
    }

    public String readValue(String path, String username) {
        String jsonPath = "$.qrpackaging[?(@.ID==\"" + username + "\")]";
        String result = "";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASENAME);
            MongoCollection<Document> dbCollection = database.getCollection(path);
            Iterable<Document> collection = dbCollection.find();
            for (var doc: collection) {
                result = doc.toJson();
            }
        }
        return result;
    }

    public String login(String username, String password)
    {
        String path = "$.User[?(@.Username==\"" + username + "\")].Password";
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            String jsonResult = "";
            MongoDatabase database = mongoClient.getDatabase(DATABASENAME);
            MongoCollection<Document> dbCollection = database.getCollection("User");
            Iterable<Document> collection = dbCollection.find();
            for (var doc: collection) {
                jsonResult = doc.toJson();
            }
            ReadContext ctx = JsonPath.parse(jsonResult);
            List<String> recvPassword = ctx.read(path);

            if(recvPassword.get(0).equals(password)){
                return "Success";
            }
            else {
                return "Fail";
            }
        }
    }

    public String writeBundle(String username, String senderAddress, String receiverAddress) {
        PackageList pl1 = new PackageList("123456", "968431187", receiverAddress, senderAddress);
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        ConnectionString connString = new ConnectionString(
                "mongodb://localhost:27017"
        );
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .retryWrites(true)
                .build();

        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("qrpackaging");
            MongoCollection<Document> testCollection = database.getCollection("User");

            Bson filter = Filters.eq("ID", username);
            Bson updateOperation = Updates.addToSet("PackageList", pl1.toBsonDoc());
            UpdateResult updateResult = testCollection.updateOne(filter, updateOperation);

            System.out.println("=> Updating the doc with {\"ID\":"+ username +"}. Adding comment.");
            System.out.println(testCollection.find(filter).first().toJson(prettyPrint));
            System.out.println(updateResult);
        }
        return "";
    }
}
