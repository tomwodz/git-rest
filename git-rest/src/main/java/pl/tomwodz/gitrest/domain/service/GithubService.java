package pl.tomwodz.gitrest.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.Branch;
import pl.tomwodz.gitrest.domain.model.SampleViewResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.GithubProxy;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.BranchesByUsernameResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class GithubService {


    private final GithubProxy githubProxy;

    private List<ReposByUsernameResponseDto> makeGetRequestByUsername(String username){
        return this.githubProxy.makeGetRequestReposByUsername(username);
    }

    private List<BranchesByUsernameResponseDto> makeGetRequestByUsernameAndRepoName(String username, String repo){
        return this.githubProxy.makeGetRequestByUsernameAndRepoName(username, repo);
    }

    public List<SampleViewResponseDto> makeGetRequestAllByUsername(String username){
        List<SampleViewResponseDto> sampleViewResponseDto = new ArrayList<>();
        List<ReposByUsernameResponseDto> repos = makeGetRequestByUsername(username);
        for(int i = 0; i < repos.size(); i++)
        {
            List<BranchesByUsernameResponseDto> branchesBySampleRepo = makeGetRequestByUsernameAndRepoName(username, repos.get(i).name());
            ArrayList<Branch> branches = new ArrayList<>();
            for(int j = 0; j < branchesBySampleRepo.size(); j++){
                Branch branch = new Branch(branchesBySampleRepo.get(j).name(),branchesBySampleRepo.get(j).commit().sha());
                branches.add(branch);
            }
            sampleViewResponseDto.add(new SampleViewResponseDto(repos.get(i).name(),username,branches));
        }
        return sampleViewResponseDto;
    }

    public List<ReposByUsernameResponseDto> makeGetRequestAllReposByUsername(String username){
        List<ReposByUsernameResponseDto> repos = makeGetRequestByUsername(username);
        return repos;
    }

}
