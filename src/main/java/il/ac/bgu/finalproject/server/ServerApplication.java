package il.ac.bgu.finalproject.server;
import il.ac.bgu.finalproject.server.CommunicationLayer.DTOs.Converter;
import il.ac.bgu.finalproject.server.Domain.Controllers.AdminClientController;
import il.ac.bgu.finalproject.server.Domain.Controllers.RegularClientController;
import il.ac.bgu.finalproject.server.ServiceLayer.IService;
import il.ac.bgu.finalproject.server.ServiceLayer.Service;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Bean
	public IService service() {
		return new Service();
	}

	@Bean
	public RegularClientController regularClientController() {
		return new RegularClientController(); }

	@Bean
	public AdminClientController adminClientController() {
		return new AdminClientController();
	}

	@Bean
	public Converter converter() {
		return new Converter();
	}

}
