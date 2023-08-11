package pl.tomwodz.gitrest.git.infrastructure.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.git.infrastructure.error.model.RepoNotFoundException;
import pl.tomwodz.gitrest.git.infrastructure.repository.IRepoRepository;
import pl.tomwodz.gitrest.git.infrastructure.service.IRepoService;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
@Transactional
public class RepoService implements IRepoService {

    private final IRepoRepository repoRepository;

    @Override
    public List<Repo> findAll() {
        log.info("findAll repos");
        return this.repoRepository.findAll();
    }

    @Override
    public Repo findById(Long id) {
        return this.repoRepository.findById(id)
                .orElseThrow(() -> new RepoNotFoundException("Repo with id " + id + " not found."));
    }

    @Override
    public Repo addRepo(Repo repo) {
        log.info("adding new repo: " + repo);
        return repoRepository.save(repo);
    }

    @Override
    public void deleteById(Long id) {
        existsById(id);
        log.info("deleting repo by id: " + id);
        repoRepository.deleteById(id);
    }

    @Override
    public void existsById(Long id) {
        if (!repoRepository.existsById(id)) {
            throw new RepoNotFoundException("Repo with id " + id + " not found.");
        }
    }

    @Override
    public void updateById(Long id, Repo newRepo) {
        existsById(id);
        repoRepository.updateById(id, newRepo);
        log.info("Updated repo with id: " + id);
    }

    @Override
    public List<Repo> addListRepos(List<Repo> repos) {
        log.info("adding new repos form GITHUB: ");
        return repos.stream()
                .map(repo -> repoRepository.save(repo))
                .toList();
    }
}
