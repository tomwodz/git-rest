package pl.tomwodz.gitrest.git.infrastructure.error.model;

public class RepoNotFoundException extends RuntimeException {
    public RepoNotFoundException(String message) {
        super(message);
    }
}
