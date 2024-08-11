package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	
	public Users findByEmail(String email);
	public Page<Users>findAll(Pageable pageable);
	public Page<Users>findAllByEmailLike(String email,Pageable pageable);
	public Page<Users>findAllByNameLike(String name,Pageable pageable);
	
	public List<Users>findByNameLikeOrderByCreatedAtDesc(String name);
}
