package pl.tomwodz.gitrest.git.infrastructure.controller.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateRepoResponseDto(
        @NotNull(message = "owner must not be null")
        @NotEmpty(message = "owner must not be empty")
        String owner,

        @NotNull(message = "name must not be null")
        @NotEmpty(message = "name must not be empty")
        String name
) {
}
