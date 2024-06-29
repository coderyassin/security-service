package org.yascode.security_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.security_service.controller.api.HomeApi;

@RestController
@RequestMapping(value = "/home")
public class HomeController implements HomeApi {
    @Override
    public ResponseEntity<?> mainApi() {
        return ResponseEntity.ok().body("Main API");
    }

    @Override
    public ResponseEntity<?> home() {
        return ResponseEntity.ok().body("Home API");
    }
}
