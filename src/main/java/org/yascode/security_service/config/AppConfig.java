package org.yascode.security_service.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.yascode.security_service.security.Role;
import org.yascode.security_service.security.User;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "security")
@Getter
@Setter
public class AppConfig {
    private List<User> users;
    private List<Role> roles;
}
