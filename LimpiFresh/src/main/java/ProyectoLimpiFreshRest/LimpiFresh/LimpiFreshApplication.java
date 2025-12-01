package ProyectoLimpiFreshRest.LimpiFresh;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API Limpifresh",
				version = "1.0",
				description = "API para gestión de productos de limpieza (Limpifresh).",
				contact = @Contact(
						name = "Limpifresh"
				)
		),
		servers = {
				@Server(
						url = "http://localhost:8080",
						description = "Servidor local"
				)
		}
)
public class LimpiFreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimpiFreshApplication.class, args);
	}

}
