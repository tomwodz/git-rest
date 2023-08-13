package pl.tomwodz.gitrest.domain.service;

import pl.tomwodz.gitrest.domain.model.Repo;

public interface IRepoUpdater {
    void updateById(Long id, Repo newRepo);
}
