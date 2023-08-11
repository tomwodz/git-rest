package pl.tomwodz.gitrest.git.infrastructure.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tomwodz.gitrest.git.infrastructure.error.model.RepoNotFoundException;
import pl.tomwodz.gitrest.git.infrastructure.controller.RepoRestController;
import pl.tomwodz.gitrest.git.infrastructure.error.response.RepoGitResponseDto;

@ControllerAdvice(assignableTypes = RepoRestController.class)
@Log4j2
public class RepoErrorHandler {

    @ExceptionHandler(RepoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RepoGitResponseDto handleException(RepoNotFoundException exception){
        log.warn("not existing repo");
        return new RepoGitResponseDto(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
