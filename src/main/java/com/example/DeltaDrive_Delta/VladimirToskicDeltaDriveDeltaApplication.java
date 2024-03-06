package com.example.DeltaDrive_Delta;

import com.example.DeltaDrive_Delta.entities.Role;
import com.example.DeltaDrive_Delta.entities.User;
import com.example.DeltaDrive_Delta.repository.UserRepository;
import com.example.DeltaDrive_Delta.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VladimirToskicDeltaDriveDeltaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	private VehicleRepository vehicleRepository;

	public static void main(String[] args) {
		SpringApplication.run(VladimirToskicDeltaDriveDeltaApplication.class, args);
	}


	public void run(String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount == null){
			User user = new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.saveAndFlush(user);
		}

//		List<Vehicle> vehicles = vehicleRepository.findAll();
//		for(Vehicle v : vehicles){
//			System.out.println(v.getBrand()+" je brand"+ v.getDriverFirstName()+" je vozac");
//		}

	}
}
