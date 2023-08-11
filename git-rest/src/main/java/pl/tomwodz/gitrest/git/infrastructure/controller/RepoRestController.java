package pl.tomwodz.gitrest.git.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.request.CreateRepoRequestDto;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.request.UpdateRepoRequestDto;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.response.*;
import pl.tomwodz.gitrest.git.infrastructure.service.IRepoService;

import java.util.List;

import static pl.tomwodz.gitrest.git.infrastructure.controller.RepoMapper.*;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/repo")
public class RepoRestController {

    private final IRepoService repoService;

    @GetMapping
    public ResponseEntity<GetAllReposResponseDto> getAllRepos() {
        List<Repo> allRepos = repoService.findAll();
        GetAllReposResponseDto response = mapFromRepoToGetAllReposResponseDto(allRepos);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<GetRepoResponseDto> getRepoById(@PathVariable Long id) {
        Repo repo = repoService.findById(id);
        GetRepoResponseDto response = mapFromRepoToGetRepoResponseDto(repo);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreateRepoResponseDto> postRepo(@RequestBody @Valid CreateRepoRequestDto request) {
        Repo repo = RepoMapper.mapFromCreateRepoRequestDtoToRepo(request);
        Repo saveRepo = repoService.addRepo(repo);
        CreateRepoResponseDto body = mapFromRepoToCreateResponseDto(saveRepo);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteRepoResponseDto> deleteRepoByUsingPathVariable(@PathVariable Long id) {
        repoService.deleteById(id);
        DeleteRepoResponseDto body = mapFromRepoToDeleteRepoResponseDto(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity<UpdateRepoResponseDto> updateRepo(@PathVariable Long id,
                                                            @RequestBody @Valid UpdateRepoRequestDto request){
        Repo newRepo = RepoMapper.mapFromUpdateRepoRequestDtoToRepo(request);
        repoService.updateById(id, newRepo);
        UpdateRepoResponseDto body = RepoMapper.mapFromRepoToRepoResponseDto(newRepo);
        return ResponseEntity.ok(body);
    }

}
