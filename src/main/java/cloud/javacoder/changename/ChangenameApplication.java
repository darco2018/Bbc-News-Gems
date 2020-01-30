package cloud.javacoder.changename;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ChangenameApplication {

	@Value("${spring.message: not chosen}")
	private String activeProfile;

	public static void main(String[] args) {

		ApplicationContext appContext = SpringApplication.run(ChangenameApplication.class, args);

		System.out.printf("1. Change app name in a) 2 places in pom.xml  b) in .iml c) in Main class name d) in app.props\n" +
				"2. Set profiles in Edit config\n" +
				"3. View -> remove Thymeleaf + templates folder\n" +
				"4. uncomment Security\n" +
				"5. Revise deps\n" +
				"6. LIVE RELOAD: a) REGISTRY: compiler.automake.allow.when.app.running=true & turn on:\n " +
				"   b) Build project automatically (only works when not running or debugging)\n" +
				"   c) LIVE RELOAD extension enabled in Browser! (check Chrome Dev Tools >> Network)\n" +
				"   NOTE: DOESNT WORK IN FIREFOX or .jar. Ctrl + S triggers automake\n" +
				"7. HAL-EXPLORER: localhost:8080 redirects to http://localhost:8080/explorer/index.html#/\n" +
				"8. Commit to Git\n");


	}

}
