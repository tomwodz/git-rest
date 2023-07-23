package pl.tomwodz.gitrest.git.infrastructure.controller;

import feign.FeignException;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.gitrest.domain.model.SampleViewResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.error.ErrorXmlResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.error.GithubNotFoundUsernameException;
import pl.tomwodz.gitrest.git.infrastructure.service.GithubService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class GitRestController {

    private final GithubService githubService;

    private List<SampleViewResponseDto> response = new ArrayList<>();

    public GitRestController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping(path = "/{username}", headers = "Accept=application/json")
    public ResponseEntity<List<SampleViewResponseDto>> getUsernameAllGithubRepositories(@PathVariable String username) {
        try {
            response = githubService.makeGetRequestAllByUsername(username);
        } catch (FeignException exception) {
            throw new GithubNotFoundUsernameException("not existing github user");
        }
        log.info(username);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{username}", headers = "Accept=application/xml")
    public ResponseEntity<ErrorXmlResponseDto> getResponseXmlNotUse(@PathVariable String username){
        log.info("xml not use");
        ErrorXmlResponseDto response = new ErrorXmlResponseDto(HttpStatus.BAD_REQUEST, "xml not use");
        return ResponseEntity.ok(response);
    }



}
