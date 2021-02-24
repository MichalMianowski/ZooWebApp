package zoo_webapp.zoomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZoomanagerApplication {

    public static void main(String[] args) {
        DBConnector.connect();
        SpringApplication.run(ZoomanagerApplication.class, args);
    }

}
