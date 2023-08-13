package pl.tomwodz.gitrest.domain.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pl.tomwodz.gitrest.domain.repository.IRepoRepository;
import pl.tomwodz.gitrest.domain.service.IRepoDeleter;

@Service
@Log4j2
@AllArgsConstructor
@Transactional
public class RepoDeleter implements IRepoDeleter {

    private final IRepoRepository repoRepository;
    private final RepoRetriever repoRetriever;

    @Override
    public void deleteById(Long id) {
        repoRetriever.existsById(id);
        log.info("deleting repo by id: " + id);
        repoRepository.deleteById(id);
    }
}
