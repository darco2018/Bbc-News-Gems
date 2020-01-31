package cloud.javacoder.bbcnewsgems;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BbcNewsGemsApp {

	//@Value("${spring.message}") //  not working
	private String activeProfile;

	public static void main(String[] args) {

		ApplicationContext appContext = SpringApplication.run(BbcNewsGemsApp.class, args);

		//System.out.println(activeProfile );
		System.out.printf("" +
				"6. LIVE RELOAD:" +
				"   b) Build project automatically (only works when not running or debugging)\n" +
				"   c) LIVE RELOAD extension enabled in Browser! (check Chrome Dev Tools >> Network)\n" +
				"   NOTE: DOESNT WORK IN FIREFOX or .jar. Ctrl + S triggers automake after some seconds!!! \n" +
				"7. HAL-EXPLORER: localhost:8080 redirects  to http://localhost:8080/explorer/index.html#/\n" +
				"8. ACTUATOR: localhost:8080/actuator/\n" +
				"9. H2 console available at '/h2-console'");


	}

}
