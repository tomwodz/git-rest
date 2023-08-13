package pl.tomwodz.gitrest.git.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.gitrest.domain.service.IRepoRetriever;

@Controller
@RequestMapping("/view")
@AllArgsConstructor
public class RepoViewController {

    private final IRepoRetriever repoRetriever;

    @GetMapping(path = "/")
    public String main(Model model) {
        model.addAttribute("repos", this.repoRetriever.findAll(Pageable.ofSize(100)));
        return "list";
    }

}
