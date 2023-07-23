package pl.tomwodz.gitrest.git.infrastructure.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.gitrest.git.infrastructure.controller.GitRestController;

@ControllerAdvice(assignableTypes = GitRestController.class)
@Log4j2
public class GitErrorHandler {

    @ExceptionHandler(GithubNotFoundUsernameException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorGitResponseDto handleException(GithubNotFoundUsernameException exception){
        log.warn("not existing github user");
        return new ErrorGitResponseDto(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
