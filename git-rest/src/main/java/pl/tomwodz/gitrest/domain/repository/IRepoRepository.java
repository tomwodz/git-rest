package pl.tomwodz.gitrest.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.gitrest.domain.model.Repo;

import java.util.List;
import java.util.Optional;


public interface IRepoRepository extends Repository<Repo, Long> {

    @Query("SELECT r FROM Repo r ORDER BY r.id DESC")
    List<Repo> findAll(Pageable pageable);

    @Query("SELECT r FROM Repo r WHERE  r.id =:id ORDER BY r.id DESC")
    Optional<Repo> findById(Long id);

    @Query("SELECT r FROM Repo r WHERE  r.owner =:owner ORDER BY r.id DESC")
    List<Repo> findByOwner(String owner);

    Repo save(Repo repo);

    boolean existsById(Long id);

    @Modifying
    @Query("DELETE FROM Repo r WHERE r.id = :id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Repo r SET r.owner = :#{#newRepo.owner}, r.name = :#{#newRepo.name} WHERE r.id = :id")
    void updateById(Long id, Repo newRepo);

    boolean existsByOwner(String owner);
}
