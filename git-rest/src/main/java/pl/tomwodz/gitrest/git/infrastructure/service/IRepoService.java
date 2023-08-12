package pl.tomwodz.gitrest.git.infrastructure.service;


import org.springframework.data.domain.Pageable;
import pl.tomwodz.gitrest.domain.model.Repo;

import java.util.List;

public interface IRepoService {
    List<Repo> findAll(Pageable pageable);

    Repo findById(Long Id);

    Repo addRepo(Repo repo);

    void deleteById(Long id);

    void existsById(Long id);

    void existsByOwner(String owner);

    void updateById(Long id, Repo newRepo);

    List<Repo> addListRepos(List<Repo> repos);

    List<Repo> findByOwner(String owner);
}
