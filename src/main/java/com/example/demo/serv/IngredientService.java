package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.pojo.Ingredient;
import com.example.demo.repo.IngredientRepo;

public class IngredientService {
	
	@Autowired
	public IngredientRepo ingredientRepo;
	
	public void save(Ingredient ingredient) {
		ingredientRepo.save(ingredient);
	}
	
	public List<Ingredient> findAll(){
		return ingredientRepo.findAll();
	}

	public Optional<Ingredient> getPizzaById(int id) {
		return ingredientRepo.findById(id);
	}

	public void delete(int id) {
		ingredientRepo.deleteById(id);
	}
}
