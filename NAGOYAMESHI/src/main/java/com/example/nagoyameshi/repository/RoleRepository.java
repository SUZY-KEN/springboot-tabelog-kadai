package com.example.nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {

	public Roles findByName(String name);
}
