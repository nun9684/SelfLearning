package com.qrpackaging.backend.qrpackaging.model;

import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

import java.util.HashMap;

public class PackageList{
    String packageID;
    String credential;
    String receiverAddress;
    String senderAddress;

    public PackageList (){
        this.packageID = "";
        this.credential = "";
        this.receiverAddress = "";
        this.senderAddress = "";
    }

    public PackageList (String packageID, String credential, String receiverAddress, String senderAddress){
        this.packageID = packageID;
        this.credential = credential;
        this.receiverAddress = receiverAddress;
        this.senderAddress = senderAddress;
    }

    public Document toBsonDoc() {
        Document document = new Document();

        document.append("PackageID", this.packageID);
        document.append("Credential", this.credential);
        document.append("SenderAddress", this.senderAddress);
        document.append("ReceiverAddress", this.receiverAddress);

        return document;
    }
}
