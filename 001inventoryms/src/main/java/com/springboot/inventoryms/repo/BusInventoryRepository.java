package com.springboot.inventoryms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.inventoryms.model.BusInventory;

public interface BusInventoryRepository extends JpaRepository<BusInventory, Long> {
}
