package com.example.demo.controller;

import java.util.List;

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
		
		Ingredient ingredient = ingredientService.getById(id);	
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
		
		Ingredient ingredients = ingredientService.getById(id);
		model.addAttribute("ingredient", ingredients);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
	
		return "ingredient-update";
	}
	
	@PostMapping("/update/{id}")
	public String updateIngredient(@PathVariable("id") int id, @Valid Ingredient ingredient) {
		
		Ingredient oldIngredient = ingredientService.getById(id);
		
		for (Pizza pizza : oldIngredient.getPizzas()) 
			pizza.getIngredients().remove(ingredient);
		
		for (Pizza pizza : ingredient.getPizzas())			
			pizza.getIngredients().add(ingredient);
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable("id") int id) {

		ingredientService.delete(id);
		
		return "redirect:/ingredient";
	}
	
}
