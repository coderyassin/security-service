package org.yascode.security_service.controller.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithAuthorities {
    private String username;
    private String password;
    private Boolean enableUser;
    private List<String> authorities;
}
