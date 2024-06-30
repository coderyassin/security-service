package org.yascode.security_service.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestError {
    String errorCode;
    String errorMessage;
}
