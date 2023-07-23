package pl.tomwodz.gitrest.git.infrastructure.error;

public class GithubNotFoundUsernameException extends RuntimeException {

    public GithubNotFoundUsernameException(String message) {
        super(message);
    }
}
