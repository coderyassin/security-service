package org.yascode.security_service.controller.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddAuthoritiesForUserPrincipal {
    private String username;
    private List<String> authority;
}
