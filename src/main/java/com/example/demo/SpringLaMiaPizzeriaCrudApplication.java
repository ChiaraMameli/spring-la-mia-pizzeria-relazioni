package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.pojo.Drink;
import com.example.demo.pojo.Pizza;
import com.example.demo.pojo.Promotion;
import com.example.demo.serv.DrinkService;
import com.example.demo.serv.PizzaService;
import com.example.demo.serv.PromotionService;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private DrinkService drinkService;	
	@Autowired
	private PromotionService promotionService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Promotion promotion1 = new Promotion(LocalDate.parse("2022-12-12"), LocalDate.parse("2023-12-01"), "Buy 3 pay 2");
		Promotion promotion2 = new Promotion(LocalDate.parse("2022-12-12"), LocalDate.parse("2023-12-02"), "Buy 2 pay 1");
		promotionService.save(promotion2);
		promotionService.save(promotion1);
		
		Pizza pizza1 = new Pizza("Margherita", "Buonissima" , 600, promotion1);
		Pizza pizza2 = new Pizza("Napoli", "Buonissima" , 450, promotion2);
		Pizza pizza3 = new Pizza("Nutella", "Buonissima" , 700, promotion1);
		Drink drink1 = new Drink("Coca Cola", "Freschissima", 250);
		Drink drink2 = new Drink("Ichnusa", "Freschissima", 300);
		Drink drink3 = new Drink("Acqua", "Freschissima", 100);
		
		pizzaService.save(pizza1);
		pizzaService.save(pizza2);
		pizzaService.save(pizza3);
		
		drinkService.save(drink1);
		drinkService.save(drink2);
		drinkService.save(drink3);
		
//		System.err.println("----------TEST-------------");
//		
//		System.out.println("----------FIND ALL-------------");
//		System.out.println(promotionService.findAll());
//		
//		System.out.println("----------DELETE-------------");
//		promotionService.deleteById(1);
//		
//		System.out.println("----------FIND ALL AFTER DELETE-------------");
//		System.out.println(promotionService.findAll());
//		
//		System.out.println("----------FIND ALL PIZZA-------------");
//		List<Promotion> promotions = promotionService.findAllPizza();
//		
//		for (Promotion promotion : promotions) {
//			
//			System.err.println(promotion);
//			
//			for (Pizza pizza : promotion.getPizza()) {
//				
//				System.err.println("\t" + pizza);
//			}
//		}

	}

}