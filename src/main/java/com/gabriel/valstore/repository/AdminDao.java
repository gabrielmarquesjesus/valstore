package com.gabriel.valstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.valstore.entity.Admin;

public interface AdminDao extends JpaRepository<Admin, Long>{
    
}
