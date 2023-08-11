package pl.tomwodz.gitrest.git.infrastructure.service;


import pl.tomwodz.gitrest.domain.model.Repo;

import java.util.List;

public interface IRepoService {
    List<Repo> findAll();

    Repo findById(Long Id);

    Repo addRepo(Repo repo);

    void deleteById(Long id);

    void existsById(Long id);

    void updateById(Long id, Repo newRepo);
}
