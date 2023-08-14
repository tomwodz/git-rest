package pl.tomwodz.gitrest.git.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.gitrest.domain.model.Repo;
import pl.tomwodz.gitrest.domain.service.*;
import pl.tomwodz.gitrest.git.infrastructure.error.model.RepoNotFoundException;
import pl.tomwodz.gitrest.git.infrastructure.proxy.dto.response.ReposByUsernameResponseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static pl.tomwodz.gitrest.git.infrastructure.controller.RepoMapper.mapFromListReposByUsernameResponseDtoToListRepo;

@Controller
@RequestMapping("/view")
@AllArgsConstructor
@Log4j2
public class RepoViewController {

    private final IRepoRetriever repoRetriever;
    private final IRepoDeleter repoDeleter;
    private final IRepoUpdater repoUpdater;
    private final IRepoAdder repoAdder;
    private  final IGithubRetriever githubRetriever;

    @GetMapping(path = "/")
    public String main(Model model) {
        model.addAttribute("repos", this.repoRetriever.findAll(Pageable.ofSize(1000)));
        return "list";
    }

    @GetMapping(path = "/owner/")
    public String getReposByOwner(Model model, @RequestParam(required = false) String owner) {
        try {
            if(owner == null){
            model.addAttribute("repos", Collections.emptyList());
            return "owner";
            }
            model.addAttribute("repos", this.repoRetriever.findByOwner(owner));
            return "owner";
        } catch (RepoNotFoundException e) {
            model.addAttribute("error_message", e.getMessage());
            return "error_message";
        }
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteRepoById(Model model, @PathVariable Long id) {
        try {
            this.repoDeleter.deleteById(id);
            model.addAttribute("info_message", "Deleted repo with id: " + id);
            return "info_message";
        } catch (RepoNotFoundException e) {
            model.addAttribute("error_message", e.getMessage());
            return "error_message";
        }
    }

    @GetMapping(path = "/update/{id}")
    public String getRepoByIdToUpdate(Model model, @PathVariable Long id) {
        try {
            Repo repoBox = this.repoRetriever.findById(id);
            model.addAttribute("repoModel", repoBox);
            return "add-repo";
        } catch (RepoNotFoundException e) {
            model.addAttribute("error_message", e.getMessage());
            return "error_message";
        }
    }

    @PostMapping(path = "/update/{id}")
    public String updateRepoById(@ModelAttribute Repo repo, Model model) {
        try {
            this.repoUpdater.updateById(repo.getId(), repo);
            model.addAttribute("info_message", "Updated repo with id: " + repo.getId());
            return "info_message";
        } catch (RepoNotFoundException e) {
            model.addAttribute("error_message", e.getMessage());
            return "error_message";
        }
    }

    @GetMapping(path ="/add/")
    public String addRepo(Model model) {
        model.addAttribute("repoModel", new Repo());
        return "add-repo";
    }

    @PostMapping (path ="/add/")
    public String addRepoToDatabase(@ModelAttribute Repo repo, Model model) {
        if (repo.getOwner().equals("") || repo.getName().equals(""))
        {
                model.addAttribute("repoModel", new Repo());
                return "add-repo";
        }
        try{
            this.repoAdder.addRepo(repo);
            model.addAttribute("info_message", "Add repo to database.");
            return "info_message";
        } catch (Exception e){
            return "index";
        }
    }

    @GetMapping(path ="/client/")
    public String getRequest(Model model) {
        model.addAttribute("requestModel", new Repo());
        return "client";
    }

    @PostMapping (path ="/client/")
    public String getRequestToGithub(@ModelAttribute Repo repo, Model model) {
        if (repo.getOwner().equals(""))
        {
            model.addAttribute("requestModel", new Repo());
            model.addAttribute("reposByGithub", new ArrayList<>());
            return "client";
        }
        try {
            String username = repo.getOwner();
            List<ReposByUsernameResponseDto> request = this.githubRetriever.makeGetRequestByUsername(username);
            List<Repo> reposToSave = mapFromListReposByUsernameResponseDtoToListRepo(request, username);
            List<Repo> reposSaved = this.repoAdder.addListRepos(reposToSave);
            model.addAttribute("requestModel", repo);
            model.addAttribute("reposByGithub", reposSaved);
            return "client";
        } catch (Exception e){
            model.addAttribute("error_message", e.getMessage());
            return "error_message";
        }
    }



}
