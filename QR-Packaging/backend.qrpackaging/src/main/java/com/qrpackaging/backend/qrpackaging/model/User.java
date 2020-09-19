package com.qrpackaging.backend.qrpackaging.model;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String ID;
    private List<PackageList> packageLists = new ArrayList<PackageList>();

    public User() {}

    public User(String id, ArrayList<PackageList> packageLists) {
        this.ID = id;
        this.packageLists = packageLists;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public List<PackageList> getPackageLists() {
        return packageLists;
    }

    public void setPackageLists(ArrayList<PackageList> packageLists) {
        this.packageLists = packageLists;
    }

    public Document toBsonDoc() {
        Document document = new Document();
        List<Document> documents = new ArrayList<Document>();

        document.append("ID", this.ID);
        packageLists.forEach(data -> documents.add(data.toBsonDoc()));
        document.append("PackageList", documents);

        return document;
    }
}
