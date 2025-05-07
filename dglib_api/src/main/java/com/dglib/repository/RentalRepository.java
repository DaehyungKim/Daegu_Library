package com.dglib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dglib.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
