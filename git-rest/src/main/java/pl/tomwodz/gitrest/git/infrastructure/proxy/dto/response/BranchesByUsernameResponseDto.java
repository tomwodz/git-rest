package pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response;

import pl.tomwodz.gitrest.domain.model.Commit;

public record BranchesByUsernameResponseDto(
        String name,
        Commit commit
) {
}
