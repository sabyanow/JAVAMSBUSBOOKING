package com.springboot.inventoryms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.inventoryms.model.BusInventory;
import com.springboot.inventoryms.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{busNumber}")
    public BusInventory getInventory(@PathVariable Long busNumber) {
        return inventoryService.getInventory(busNumber);
    }

    @PutMapping("/{busNumber}")
    public BusInventory updateInventory(@PathVariable Long busNumber, @RequestBody BusInventory busInventory) {
        return inventoryService.updateInventory(busNumber, busInventory);
    }
}
