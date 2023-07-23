package pl.tomwodz.gitrest.git.infrastructure.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.BranchesByUsernameResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;

import java.util.List;


@FeignClient(value= "github-client")
@Component
public interface GithubProxy {

    @GetMapping(value = "/users/{username}/repos")
    List<ReposByUsernameResponseDto> makeGetRequestReposByUsername(@PathVariable String username);

    @GetMapping(value = "/repos/{username}/{repo}/branches")
    List<BranchesByUsernameResponseDto> makeGetRequestByUsernameAndRepoName(@PathVariable String username, @PathVariable String repo);
}
