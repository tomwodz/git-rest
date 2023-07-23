package pl.tomwodz.gitrest.git.infrastructure.error;

import org.springframework.http.HttpStatus;

public record ErrorGitResponseDto(HttpStatus status, String Message) {
}
