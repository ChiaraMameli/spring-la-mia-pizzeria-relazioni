package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Pizza;
import com.example.demo.repo.PizzaRepo;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepo pizzaRepo;
	
	public void save(Pizza pizza) {
		pizzaRepo.save(pizza);
	}
	
	public List<Pizza> findAll(){
		return pizzaRepo.findAll();
	}

	public Optional<Pizza> getPizzaById(int id) {
		return pizzaRepo.findById(id);
	}

	public void delete(int id) {
		pizzaRepo.deleteById(id);
	}
	
	public List<Pizza> findByName(String name) {
		return pizzaRepo.findByNameContainingIgnoreCase(name);
	}
	
	@Transactional
	public List<Pizza> findAllWIngredient() {
		List<Pizza> pizzas = pizzaRepo.findAll();
		
		for (Pizza pizza : pizzas) {
			Hibernate.initialize(pizza.getIngredients());
		}
		
		return pizzas;
		
	}


}
