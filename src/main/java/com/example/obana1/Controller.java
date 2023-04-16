package com.example.obana1;

import com.example.obana1.database.DBloader;
import com.example.obana1.database.SchemeLoader;
import com.example.obana1.config.DataGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    SchemeLoader schemeLoader;

    @Autowired
    DataGenerator dataGenerator;

    @Autowired
    DBloader dBloader;
    @GetMapping("/") public ResponseEntity<String> home() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN,
                "*");
        responseHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");

        try {
            System.out.println("get_message");

            ObjectMapper objectMapper = new ObjectMapper();

            return ResponseEntity.ok().headers(responseHeaders).body(
                    objectMapper.writeValueAsString(schemeLoader.readScheme().getTableMap().values())
            );
        } catch (JsonProcessingException exception) {
            return ResponseEntity.ok().headers(responseHeaders).body(
                    "Failed to convert"
            );
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> regRules(@RequestBody String rules){

        System.out.println(" ");
        System.out.println(rules);

        String[] ss = rules.split("&");
        String table_name = ss[ss.length - 1].split("-")[0];

        System.out.println(table_name);

        dBloader.fillWithSinthetic(dataGenerator.parseRule(rules), table_name);

        return ResponseEntity.ok().body("");
    }

}
