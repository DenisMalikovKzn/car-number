package com.example.carnumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/random")
    public ResponseEntity<String> random() {
        return new ResponseEntity<>(service.random(), HttpStatus.OK);
    }

    @GetMapping("/next")
    public ResponseEntity<String> next() {
        return new ResponseEntity<>(service.next(), HttpStatus.OK);
    }
}
