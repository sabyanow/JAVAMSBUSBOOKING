package com.springboot.adminms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.adminms.model.BusRoute;
import com.springboot.adminms.service.BusRouteService;

@RestController
@RequestMapping("/busroutes")
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @PostMapping
    public BusRoute addBusRoute(@RequestBody BusRoute busRoute) {
        return busRouteService.addBusRoute(busRoute);
    }

    @PutMapping("/{id}")
    public BusRoute updateBusRoute(@PathVariable Long id, @RequestBody BusRoute busRoute) {
        return busRouteService.updateBusRoute(id, busRoute);
    }

    @DeleteMapping("/{id}")
    public void deleteBusRoute(@PathVariable Long id) {
        busRouteService.deleteBusRoute(id);
    }
}
