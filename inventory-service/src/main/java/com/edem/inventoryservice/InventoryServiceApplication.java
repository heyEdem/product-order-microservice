package com.edem.inventoryservice;

import com.edem.inventoryservice.model.Inventory;
import com.edem.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Iphone");
			inventory.setQuantity(24);


			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Samsung");
			inventory1.setQuantity(0);

			repository.save(inventory);
			repository.save(inventory1);
		};
	}
}
