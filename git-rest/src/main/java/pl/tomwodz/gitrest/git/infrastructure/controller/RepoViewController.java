package pl.tomwodz.gitrest.git.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tomwodz.gitrest.git.infrastructure.service.IRepoService;

@Controller
@RequestMapping("/view")
@AllArgsConstructor
public class RepoViewController {

    private final IRepoService repoService;

   @GetMapping(path = "/")
    public String main(Model model){
       model.addAttribute("repos", this.repoService.findAll(Pageable.ofSize(100)));
        return "list";
    }

}
