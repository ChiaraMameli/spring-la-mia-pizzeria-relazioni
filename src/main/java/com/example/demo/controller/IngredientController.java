package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.pojo.Ingredient;
import com.example.demo.pojo.Pizza;
import com.example.demo.serv.IngredientService;
import com.example.demo.serv.PizzaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String getIngredientIndex(Model model) {
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
				
		return "ingredient-index";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Optional<Ingredient> optIngredient = ingredientService.getIngredientById(id);
		
		Ingredient ingredient = optIngredient.get();		
		model.addAttribute("ingredient", ingredient);
		
		return "ingredient-show";		
	}

	
	@GetMapping("/create")
	public String createIngredient(Model model) {
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		return "ingredient-create";
	}
	
	@PostMapping("/create")
	public String storeIngredient(@Valid Ingredient ingredient) {		
		List<Pizza> PizzasIngredient = ingredient.getPizzas();
		
		for (Pizza pizza : PizzasIngredient)
			pizza.getIngredients().add(ingredient);
		ingredientService.save(ingredient);	
		
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String editIngredient(@PathVariable("id") int id, Model model) {
		
		Ingredient ingredients = ingredientService.getIngredientById(id).get();
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("ingredient", ingredients);
		model.addAttribute("pizzas", pizzas);
		
		return "ingredient-update";
	}
	
	@PostMapping("/update")
	public String updateIngredient(@Valid Ingredient ingredient) {
		
		List<Pizza> PizzasIngredient = ingredient.getPizzas();
		
		for (Pizza pizza : PizzasIngredient)
			pizza.getIngredients().add(ingredient);
		ingredientService.save(ingredient);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {
		
		Optional<Ingredient> optIngredient = ingredientService.getIngredientById(id);
		Ingredient ingredient  = optIngredient.get();
		
		ingredientService.delete(ingredient);
		
		return "redirect:/";
	}
	
}
