package org.yascode.security_service.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface StatisticApi {
    @GetMapping(value = "/dashboard")
    ResponseEntity<?> dashboard();
}
