package ovh.major.i_want_to_be_logged_in_to_the_internship.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Log4j2
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("https://api.major.ovh:28888");
        devServer.setDescription("Server URL in Development environment");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8888");
        localServer.setDescription("Server URL in Local environment");

        Contact contact = new Contact();
        contact.setEmail("grzegorz@major.ovh");
        contact.setName("Grzegorz Major");
        contact.setUrl("https://www.major.ovh/");

        Info info = new Info()
                .title("I Want To Be Logged In To The Internship")
                .version("1.0")
                .contact(contact)
                .description("Here is my recruitment project - for the first internship. This is not a perfect solution, but it meets all the given assumptions and additionally implements tests. Currently, I have only added Swagger and resolved the remaining dependencies' conflicts with OpenApi 3.0.");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, localServer));
    }
}