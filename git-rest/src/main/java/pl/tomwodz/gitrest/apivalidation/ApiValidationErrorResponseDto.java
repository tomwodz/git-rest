package pl.tomwodz.gitrest.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> errors, HttpStatus status) {
}
