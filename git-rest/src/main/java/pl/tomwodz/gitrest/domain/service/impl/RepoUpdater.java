package pl.tomwodz.gitrest.domain.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.domain.repository.IRepoRepository;
import pl.tomwodz.gitrest.domain.service.IRepoRetriever;
import pl.tomwodz.gitrest.domain.service.IRepoUpdater;

@Service
@Log4j2
@AllArgsConstructor
@Transactional
public class RepoUpdater implements IRepoUpdater {

    private final IRepoRepository repoRepository;
    private final IRepoRetriever repoRetriever;

    @Override
    public void updateById(Long id, Repo newRepo) {
        repoRetriever.existsById(id);
        repoRepository.updateById(id, newRepo);
        log.info("Updated repo with id: " + id);
    }

}
