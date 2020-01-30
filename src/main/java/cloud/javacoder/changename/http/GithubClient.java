package cloud.javacoder.changename.http;

import cloud.javacoder.changename.githubproject.GithubProject;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class GithubClient {

    private static final String EVENT_ISSUES_URL = "https://api.github.com/repos/{owner}/{repo}/issues/events";
    private final RestTemplate template;


    public GithubClient(RestTemplateBuilder builder) {
        this.template = builder.build();
    }

    public ResponseEntity<GithubRepoEvent[]> fetchProjectsFromGithub(String userName, String projectName){
                                                                                                        // path vars to be used in String
        ResponseEntity<GithubRepoEvent[]> response = this.template.getForEntity("https://api.github.com/repos/spring-projects/spring-boot/issues/events", GithubRepoEvent[].class);
                                                                                          // what we expect: array of GithubRepoEvents - must create such a class
        System.out.println(response); // returns headers
        System.out.println("Body: " + response.getBody()); // displays only memory address
        System.out.println("Items in array provided by the body: " +  response.getBody().length);
        System.out.println(Arrays.toString( (GithubRepoEvent[]) response.getBody())); // only arr of memory addresses
        return response;
    }
/*
    public ResponseEntity<RepositoryEvent[]> fetchEvents(String orgName, String repoName) {
        System.out.println(EVENT_ISSUES_URL + ", " + orgName + ", " + repoName);
        return this.restTemplate.getForEntity(EVENT_ISSUES_URL, RepositoryEvent[].class);
    }*/
}
