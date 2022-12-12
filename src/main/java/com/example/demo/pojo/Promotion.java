package com.example.demo.pojo;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "StartDate field cannot be null")
	@NotEmpty(message = "StartDate field cannot be empty")
	private LocalDate startDate;
	
	@NotNull(message = "EndDate field cannot be null")
	@NotEmpty(message = "EndDate field cannot be empty")
	private LocalDate endDate;
	
	@Column(unique = true)
	@NotNull(message = "Title field cannot be null")
	@NotEmpty(message = "Title field cannot be empty")
	private String title;
	
	@OneToMany(mappedBy = "promotion", cascade = CascadeType.REMOVE)
	private List<Pizza> pizza;
	
	public Promotion() {}
	public Promotion(LocalDate startDate, LocalDate endDate, String title) {
		setStartDate(startDate);
		setEndDate(endDate);
		setTitle(title);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Pizza> getPizza() {
		return pizza;
	}
	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}
	
	
	@Override
	public String toString() {
		return getTitle()
				+ " (from " + getStartDate()
				+ " to " + getEndDate()
				+ ")";
	}
}
