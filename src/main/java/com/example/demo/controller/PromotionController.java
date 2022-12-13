package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.pojo.Pizza;
import com.example.demo.pojo.Promotion;
import com.example.demo.serv.PizzaService;
import com.example.demo.serv.PromotionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/create")
	public String createPromotion(Model model) {
		Promotion promotion = new Promotion();
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("promotion", promotion);
		model.addAttribute("pizzas", pizzas);
		return "promotion-create";
	}
	
	@PostMapping("/create")
	public String storePromotion(@Valid @ModelAttribute("promotion") Promotion promotion) {
		List<Pizza> pizzasPromotion = promotion.getPizza();
		for (Pizza pizza : pizzasPromotion) {
			pizza.setPromotion(promotion);
		}
		promotionService.save(promotion);		
		return "redirect:/";
	}

}
