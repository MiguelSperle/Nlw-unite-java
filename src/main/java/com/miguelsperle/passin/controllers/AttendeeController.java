package com.miguelsperle.passin.controllers;

import com.miguelsperle.passin.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {

    @GetMapping
    public ResponseEntity<Object> getTeste(){
        return ResponseHandler.generateResponse("Sucesso", HttpStatus.OK);
    }
}
