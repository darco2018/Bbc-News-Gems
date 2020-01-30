package cloud.javacoder.changename.githubproject;

import cloud.javacoder.changename.http.GithubClient;
import cloud.javacoder.changename.http.GithubRepoEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GithubProjectController {

    private final GithubProjectRepository repo;
    private final GithubClient client;

    public GithubProjectController(GithubProjectRepository repo, GithubClient client){
        this.repo = repo;
        this.client = client;
    }

    @GetMapping("/projects")
    public List<GithubProject> getProjectFromDb(){
        return this.repo.findAll();
    }

    @GetMapping("/fetch")
    @ResponseBody
    public GithubRepoEvent[] fetch(){
        ResponseEntity<GithubRepoEvent[]> response = this.client.fetchProjectsFromGithub("darco2018", "springboot");
         //return "Fetch completed";
         return response.getBody();
    }
}
