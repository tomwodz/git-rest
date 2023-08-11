package pl.tomwodz.gitrest.git.infrastructure.error.response;

import org.springframework.http.HttpStatus;

public record ErrorGitResponseDto(HttpStatus status, String Message) {
}
