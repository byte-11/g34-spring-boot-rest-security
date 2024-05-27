package uz.pdp.g34springbootsecurity.dto.web;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;
    private String body;
    private String path;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
