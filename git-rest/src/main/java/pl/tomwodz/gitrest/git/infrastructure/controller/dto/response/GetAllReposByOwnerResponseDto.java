package pl.tomwodz.gitrest.git.infrastructure.controller.dto.response;

import pl.tomwodz.gitrest.git.infrastructure.controller.RepoDto;

import java.util.List;

public record GetAllReposByOwnerResponseDto(List<RepoDto> repos) {
}
