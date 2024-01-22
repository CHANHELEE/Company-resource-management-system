package prompt.manageResources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ManageResourcesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageResourcesApplication.class, args);
	}

}
