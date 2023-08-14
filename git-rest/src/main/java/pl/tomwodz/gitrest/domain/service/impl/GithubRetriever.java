package pl.tomwodz.gitrest.domain.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.Branch;
import pl.tomwodz.gitrest.domain.model.SampleViewResponseDto;
import pl.tomwodz.gitrest.domain.service.IGithubRetriever;
import pl.tomwodz.gitrest.git.infrastructure.proxy.GithubProxy;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.BranchesByUsernameResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class GithubRetriever implements IGithubRetriever {

    private final GithubProxy githubProxy;

   public List<ReposByUsernameResponseDto> makeGetRequestByUsername(String username){
        return this.githubProxy.makeGetRequestReposByUsername(username);
    }

    public List<SampleViewResponseDto> makeGetRequestAllByUsername(String username) {
        List<SampleViewResponseDto> sampleViewResponseDto = new ArrayList<>();
        List<ReposByUsernameResponseDto> repos = this.makeGetRequestByUsername(username);
        for (int i = 0; i < repos.size(); i++) {
            List<BranchesByUsernameResponseDto> branchesBySampleRepo = makeGetRequestByUsernameAndRepoName(username, repos.get(i).name());
            ArrayList<Branch> branches = new ArrayList<>();
            for (int j = 0; j < branchesBySampleRepo.size(); j++) {
                Branch branch = new Branch(branchesBySampleRepo.get(j).name(), branchesBySampleRepo.get(j).commit().sha());
                branches.add(branch);
            }
            sampleViewResponseDto.add(new SampleViewResponseDto(repos.get(i).name(), username, branches));
        }
        return sampleViewResponseDto;
    }
    private List<BranchesByUsernameResponseDto> makeGetRequestByUsernameAndRepoName(String username, String repo) {
        return this.githubProxy.makeGetRequestByUsernameAndRepoName(username, repo);
    }

}
