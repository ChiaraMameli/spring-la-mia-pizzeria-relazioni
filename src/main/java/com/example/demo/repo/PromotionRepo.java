package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Promotion;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {
	
}
