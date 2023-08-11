package pl.tomwodz.gitrest.git.infrastructure.error.response;

import org.springframework.http.HttpStatus;

public record ErrorXmlResponseDto(HttpStatus status, String Message){
}
