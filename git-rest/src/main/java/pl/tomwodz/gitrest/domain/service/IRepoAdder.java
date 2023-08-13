package pl.tomwodz.gitrest.domain.service;

import pl.tomwodz.gitrest.domain.model.Repo;

import java.util.List;

public interface IRepoAdder  {

    Repo addRepo(Repo repo);
    List<Repo> addListRepos(List<Repo> repos);

}
