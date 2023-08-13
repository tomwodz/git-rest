package pl.tomwodz.gitrest.domain.service;

import org.springframework.data.domain.Pageable;
import pl.tomwodz.gitrest.domain.model.Repo;

import java.util.List;

public interface IRepoRetriever {
    void existsById(Long id);

    void existsByOwner(String owner);

    List<Repo> findAll(Pageable pageable);

    Repo findById(Long Id);

    List<Repo> findByOwner(String owner);

}
