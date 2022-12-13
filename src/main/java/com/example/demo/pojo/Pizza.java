package com.example.demo.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	@NotNull(message = "Name field cannot be null")
	@NotEmpty(message = "Name field cannot be empty")
	private String name;
	
	@Column
	private String description;

	@NotNull(message = "Price field cannot be null")
	private int price;

	@ManyToOne
	@JoinColumn(name="promotion_id", nullable = false)
	private Promotion promotion;
	
	@ManyToMany
	private List<Ingredient> ingredients;

	public Pizza() {}
	public Pizza(String name, String description, int price, Promotion promotion) {
		setName(name);
		setDescription(description);
		setPrice(price);
		setPromotion(promotion);
	}
	public Pizza(String name, String description, int price, Promotion promotion, List<Ingredient> ingredients) {
		this(name, description, price, promotion);
		setIngredients(ingredients);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
		
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
	public String formattedPrice() {
		float formattedPrice = getPrice() / 100;
		String convertedPrice = String.valueOf(formattedPrice) + "0€";
		return convertedPrice.replace(".", ",");
	}
	
	@Override
	public String toString() {
		return "- " + getName()
				+ "( " + getPrice() + "€) - "
				+ getDescription();
	}
	
}
