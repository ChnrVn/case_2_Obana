package com.example.obana1;

import com.example.obana1.database.SchemeLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    SchemeLoader schemeLoader;

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

}
