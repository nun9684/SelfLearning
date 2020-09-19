package com.qrpackaging.backend.qrpackaging.controller;

import com.qrpackaging.backend.qrpackaging.dbservice.MongoDBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestControllerCommon {
    @GetMapping(value = "/")
    public ResponseEntity<String> start() {
        String response = new MongoDBService("qrpackaging").readValue("User", "nunz9684");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/init")
    public ResponseEntity<String> INIT() {
        MongoDBService.TEST();
        return ResponseEntity.ok("OK");
    }



}
