package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Promotion;
import com.example.demo.repo.PromotionRepo;

import jakarta.transaction.Transactional;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepo promotionRepo;
	
	public void save(Promotion promotion) {
		promotionRepo.save(promotion);
	}
	
	public void delete(Promotion promotion) {
		promotionRepo.delete(promotion);
	}
	
	public Optional<Promotion> findById(int id) {
		
		return promotionRepo.findById(id);
	}
	
	public void deleteById(int id) {
		promotionRepo.deleteById(id);
	}
	
	public List<Promotion> findAll() {
		
		return promotionRepo.findAll();
	}
	
	@Transactional
	public List<Promotion> findAllPizza() {
		
		List<Promotion> promotions = promotionRepo.findAll();
		
		for (Promotion promotion : promotions) {
			
			Hibernate.initialize(promotion.getPizza());
		}
		
		return promotions;
	}
}
