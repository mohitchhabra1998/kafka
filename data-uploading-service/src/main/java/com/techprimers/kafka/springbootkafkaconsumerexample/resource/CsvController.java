package com.techprimers.kafka.springbootkafkaconsumerexample.resource;


import com.techprimers.kafka.springbootkafkaconsumerexample.Reader.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
@CrossOrigin(origins="http://localhost:4200")
public class CsvController {
    private CsvReader csvReader;

    @Autowired
    public CsvController(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @GetMapping("/publish")
    public String post(){
        csvReader.produceCustomerData();
        return "published successfully";
    }

}
