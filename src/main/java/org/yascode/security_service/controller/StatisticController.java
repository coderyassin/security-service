package org.yascode.security_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yascode.security_service.controller.api.StatisticApi;

@RestController
@RequestMapping(value = "/statistic")
public class StatisticController implements StatisticApi {
    @Override
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.ok("Dashboard API");
    }
}
