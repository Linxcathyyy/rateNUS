package com.rateNUS.backend;

import com.rateNUS.backend.model.Hostel;
import com.rateNUS.backend.util.DummyData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class BackendApplication {
    @GetMapping
    @CrossOrigin("http://localhost:8081/")
    public List<Hostel> getAllHostel() {
        return DummyData.hostelList;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
