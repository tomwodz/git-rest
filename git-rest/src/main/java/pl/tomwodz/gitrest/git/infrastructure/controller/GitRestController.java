package pl.tomwodz.gitrest.git.infrastructure.controller;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.domain.model.SampleViewResponseDto;
import pl.tomwodz.gitrest.domain.service.IGithubRetriever;
import pl.tomwodz.gitrest.domain.service.IRepoAdder;
import pl.tomwodz.gitrest.git.infrastructure.controller.dto.response.GetAllReposByOwnerResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.error.model.GithubNotFoundUsernameException;
import pl.tomwodz.gitrest.git.infrastructure.error.response.ErrorXmlResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;

import java.util.ArrayList;
import java.util.List;

import static pl.tomwodz.gitrest.git.infrastructure.controller.RepoMapper.mapFromListReposByUsernameResponseDtoToListRepo;
import static pl.tomwodz.gitrest.git.infrastructure.controller.RepoMapper.mapFromRepoToGetAllReposByOwnerResponseDto;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/github")
public class GitRestController {

    private final IRepoAdder repoAdder;
    private final IGithubRetriever githubRetriever;

    private List<SampleViewResponseDto> response = new ArrayList<>();

    @GetMapping(path = "/{username}")
    public ResponseEntity<List<SampleViewResponseDto>> getUsernameAllGithubRepositories(@PathVariable String username) {
        log.info("get repos and branches from github by " + username);
        try {
            response = githubRetriever.makeGetRequestAllByUsername(username);
            return ResponseEntity.ok(response);
        } catch (FeignException exception) {
            throw new GithubNotFoundUsernameException("not existing github user");
        }
    }

    @GetMapping(path = "/{username}", headers = "Accept=application/xml")
    public ResponseEntity<ErrorXmlResponseDto> getResponseXmlNotUse(@PathVariable String username){
        log.info("xml not use");
        ErrorXmlResponseDto response = new ErrorXmlResponseDto(HttpStatus.BAD_REQUEST, "xml not use");
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/repos/{username}")
    public ResponseEntity<GetAllReposByOwnerResponseDto> getUsernameAllGithubRepos(@PathVariable String username) {
        log.info("get and save repos from github by " + username);
        try {
            List<ReposByUsernameResponseDto> request = githubRetriever.makeGetRequestByUsername(username);
            List<Repo> reposToSave = mapFromListReposByUsernameResponseDtoToListRepo(request, username);
            List<Repo> reposSaved = repoAdder.addListRepos(reposToSave);
            GetAllReposByOwnerResponseDto response = mapFromRepoToGetAllReposByOwnerResponseDto(reposSaved);
            return ResponseEntity.ok(response);
        } catch (FeignException exception) {
            throw new GithubNotFoundUsernameException("not existing github user");
        }
    }
}
