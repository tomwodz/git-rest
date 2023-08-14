package pl.tomwodz.gitrest.domain.service;

import pl.tomwodz.gitrest.domain.model.SampleViewResponseDto;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;

import java.util.List;

public interface IGithubRetriever {

    public List<ReposByUsernameResponseDto> makeGetRequestByUsername(String username);

    public List<SampleViewResponseDto> makeGetRequestAllByUsername(String username);

}
