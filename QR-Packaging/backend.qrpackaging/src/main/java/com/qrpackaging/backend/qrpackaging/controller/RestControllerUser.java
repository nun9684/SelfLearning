package com.qrpackaging.backend.qrpackaging.controller;

import com.mongodb.client.model.Filters;
import com.qrpackaging.backend.qrpackaging.Utils.SerialNumberGenrator;
import com.qrpackaging.backend.qrpackaging.dbservice.MongoDBService;
import org.bson.conversions.Bson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestControllerUser {

    @PutMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody String[] body) {
        MongoDBService mongoDBService = new MongoDBService("UserInformation");
        String value = mongoDBService.login(body[0], body[1]);
        String result = "{" +
                "\"Response\": \"" + value + "\"" +
                "}";

        System.out.println("Result is " + result);
        return ResponseEntity.ok(result);
    }

    @PutMapping(path = "/submitForm")
    public ResponseEntity<String> submitForm(@RequestBody String[] addressForm) {
        ResponseEntity<String> response = new ResponseEntity<String>("Success", HttpStatus.OK);
        String result = new MongoDBService("qrpackaging")
                .writeBundle("nunkung1", "send addr", "recv addr");

        System.out.println(result);
        return response;
    }

    @RequestMapping(value = "/getAddress", method = RequestMethod.POST)
    public ResponseEntity<String> getAddress(@RequestBody String[] body) {
        String response = new MongoDBService("qrpackaging").readValue("User", body[0]);
        System.out.println("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
