package cloud.javacoder.changename.githubproject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GithubProject {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String githubUser;
    private String projectName;

    public GithubProject(String githubUser, String projectName) {
        this.githubUser = githubUser;
        this.projectName = projectName;
    }

    public GithubProject() {
    }

    /* 2 setters */

    public void setId(long id) {
        this.id = id;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /* 3 getters */

    public long getId() {
        return id;
    }

    public String getGithubUser() {
        return githubUser;
    }

    public String getProjectName() {
        return projectName;
    }
}
