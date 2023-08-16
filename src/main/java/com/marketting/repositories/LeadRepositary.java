package com.marketting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marketting.entites.Lead;

public interface LeadRepositary extends JpaRepository<Lead, Long> {

}
