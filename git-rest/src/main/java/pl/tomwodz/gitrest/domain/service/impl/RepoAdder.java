package pl.tomwodz.gitrest.domain.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.domain.repository.IRepoRepository;
import pl.tomwodz.gitrest.domain.service.IRepoAdder;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
@Transactional
public class RepoAdder implements IRepoAdder {

    private final IRepoRepository repoRepository;

    @Override
    public Repo addRepo(Repo repo) {
        log.info("adding new repo: " + repo);
        return repoRepository.save(repo);
    }

    @Override
    public List<Repo> addListRepos(List<Repo> repos) {
        log.info("adding new repos form GITHUB: ");
        return repos.stream()
                .map(repo -> repoRepository.save(repo))
                .toList();
    }
}
