package com.parkalot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservationController {

    @GetMapping("/test")
    public String testAPI() {
        return "PARKALOT Backend is Working!";
    }
}