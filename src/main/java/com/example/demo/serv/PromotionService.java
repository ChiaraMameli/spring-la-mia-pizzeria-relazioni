package com.example.demo.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Promotion;
import com.example.demo.repo.PromotionRepo;

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
}
