package pl.tomwodz.gitrest.git.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteRepoResponseDto(String message, HttpStatus status) {
}
