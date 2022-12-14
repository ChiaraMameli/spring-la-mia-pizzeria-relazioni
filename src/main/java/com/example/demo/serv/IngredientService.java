package com.example.demo.serv;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Ingredient;
import com.example.demo.repo.IngredientRepo;

import jakarta.transaction.Transactional;

@Service
public class IngredientService {
	
	@Autowired
	public IngredientRepo ingredientRepo;
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public List<Ingredient> findAll(){
		return ingredientRepo.findAll();
	}

	public Ingredient getById(int id) {
		
		return ingredientRepo.findById(id).get();
	}

	public void delete(int id) {
		ingredientRepo.deleteById(id);
	}
	
	@Transactional
	public List<Ingredient> findAllWPizza() {
		List<Ingredient> ingredients = ingredientRepo.findAll();
		
		for (Ingredient ingredient : ingredients) {
			Hibernate.initialize(ingredient.getPizzas());
		}
		
		return ingredients;
		
	}

}
