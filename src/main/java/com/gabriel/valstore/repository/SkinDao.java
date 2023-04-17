package com.gabriel.valstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.valstore.entity.Skin;

public interface SkinDao extends JpaRepository<Skin, Long>{
    
}
