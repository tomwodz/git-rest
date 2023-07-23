package pl.tomwodz.gitrest.git.infrastructure.service;

import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.*;
import pl.tomwodz.gitrest.git.infrastructure.proxy.GithubProxy;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.BranchesByUsernameResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;
import pl.tomwodz.gitrest.domain.model.SampleViewResponseDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class GithubService {

    private final GithubProxy githubProxy;

    public GithubService(GithubProxy githubProxy) {
        this.githubProxy = githubProxy;
    }

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


}
