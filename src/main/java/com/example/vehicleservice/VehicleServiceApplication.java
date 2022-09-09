package com.example.vehicleservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class VehicleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
	}

}

@Component
@AllArgsConstructor
class VehicleInjector implements ApplicationRunner {

	private VehicleRepository repository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var vehicles = Flux.just("Toyota", "Suzuki", "BMW", "Audi", "Mazda", "Fiat")
				.map(name -> new Vehicle(null, name))
				.flatMap(vehicle -> repository.save(vehicle));
		vehicles.subscribe(System.out::println);
	}
}

@Controller
@ResponseBody
@RequestMapping("/vehicles")
@AllArgsConstructor
class VehicleController {

	private VehicleRepository repository;

	@GetMapping("/")
	public Flux<Vehicle> getVehicles(){
		return repository.findAll();
	}


}

interface VehicleRepository extends ReactiveCrudRepository<Vehicle, Integer>{};

@AllArgsConstructor
@NoArgsConstructor
@Data
class Vehicle {
	@Id
	private Integer id;
	private String model;
}
