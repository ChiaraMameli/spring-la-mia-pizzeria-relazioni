package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.pojo.Ingredient;
import com.example.demo.pojo.Pizza;
import com.example.demo.pojo.Promotion;
import com.example.demo.serv.IngredientService;
import com.example.demo.serv.PizzaService;
import com.example.demo.serv.PromotionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.getPizzaById(id);
		
		Pizza pizza = optPizza.get();		
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";		
	}
	
	@GetMapping("/create")
	public String createPizza(Model model) {
		Pizza pizza = new Pizza();
		List<Promotion> promotions = promotionService.findAll();
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("pizza", pizza);
		model.addAttribute("promotions", promotions);
		model.addAttribute("ingredients", ingredients);
		return "pizza-create";
	}
	
	@PostMapping("/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

			if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/pizza/create";
			}
		
		pizzaService.save(pizza);		
		return "redirect:/";
	}
	
	@GetMapping("/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.getPizzaById(id);
		List<Ingredient> ingredients = ingredientService.findAll();
		
		Pizza pizza = optPizza.get();		
		model.addAttribute("pizza", pizza);
		model.addAttribute("ingredients", ingredients);
		
		return "pizza-update";
	}
	
	@PostMapping("/update")
	public String updatePizza(@Valid Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
			if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/";
			}
			
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		pizzaService.delete(id);
		
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String searchDrinkByName(Model model, @RequestParam(name = "q", required = false) String query) {
		
		List<Pizza> pizza = query == null 
							? pizzaService.findAll()
							: pizzaService.findByName(query); 
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("query", query);
		
		return "pizza-search";
	}


}
