package pl.tomwodz.gitrest.git.infrastructure.error;

import org.springframework.http.HttpStatus;

public record ErrorXmlResponseDto(HttpStatus status, String Message){
}
