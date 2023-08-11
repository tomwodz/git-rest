package pl.tomwodz.gitrest.git.infrastructure.controller;

import org.springframework.http.HttpStatus;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.request.CreateRepoRequestDto;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.response.*;

import java.util.List;

public class RepoMapper {

    public  static RepoDto mapFromRepoToRepoDto(Repo repo){
        return new RepoDto(repo.getId(), repo.getOwner(), repo.getName());
    }
    public static GetAllReposResponseDto mapFromRepoToGetAllReposResponseDto(List<Repo> repos) {
        List<RepoDto> repoDtos = repos.stream()
                .map(RepoMapper::mapFromRepoToRepoDto)
                .toList();
        return new GetAllReposResponseDto(repoDtos);
    }


    public static GetRepoResponseDto mapFromRepoToGetRepoResponseDto(Repo repo) {
        RepoDto repoDto = mapFromRepoToRepoDto(repo);
        return new GetRepoResponseDto(repoDto);
    }

    public static Repo mapFromCreateRepoRequestDtoToRepo(CreateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.name());
    }

    public static CreateRepoResponseDto mapFromRepoToCreateResponseDto(Repo repo) {
        RepoDto repoDto = mapFromRepoToRepoDto(repo);
        return new CreateRepoResponseDto(repoDto);
    }

    public static DeleteRepoResponseDto mapFromRepoToDeleteRepoResponseDto(Long id) {
        return new DeleteRepoResponseDto("You deleted repo with id: " + id, HttpStatus.OK);
    }

    public static Repo mapFromUpdateRepoRequestDtoToRepo(UpdateRepoRequestDto dto) {
        return new Repo(dto.owner(), dto.name());
    }

    public static UpdateRepoResponseDto mapFromRepoToRepoResponseDto(Repo newRepo) {
        return new UpdateRepoResponseDto(newRepo.getOwner(), newRepo.getName());
    }
}
