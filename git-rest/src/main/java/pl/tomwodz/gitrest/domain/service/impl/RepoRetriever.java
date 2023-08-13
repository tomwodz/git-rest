package pl.tomwodz.gitrest.domain.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.domain.repository.IRepoRepository;
import pl.tomwodz.gitrest.domain.service.IRepoRetriever;
import pl.tomwodz.gitrest.git.infrastructure.error.model.RepoNotFoundException;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class RepoRetriever implements IRepoRetriever {

    private final IRepoRepository repoRepository;

    @Override
    public void existsById(Long id) {
        if (!repoRepository.existsById(id)) {
            throw new RepoNotFoundException("Repo with id " + id + " not found.");
        }
    }

    @Override
    public List<Repo> findAll(Pageable pageable) {
        log.info("findAll repos");
        return this.repoRepository.findAll(pageable);
    }

    @Override
    public Repo findById(Long id) {
        return this.repoRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id " + id + " not found."));
    }

    @Override
    public List<Repo> findByOwner(String owner) {
        log.info("findAll repos from the database from " + owner);
        this.existsByOwner(owner);
        return this.repoRepository.findByOwner(owner);
    }

    @Override
    public void existsByOwner(String owner) {
        if (!repoRepository.existsByOwner(owner)) {
            throw new RepoNotFoundException("Repo with owner " + owner + " not found.");
        }
    }

}
