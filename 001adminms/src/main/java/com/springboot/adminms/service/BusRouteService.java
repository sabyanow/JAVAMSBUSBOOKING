package com.springboot.adminms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.adminms.model.BusRoute;
import com.springboot.adminms.repo.BusRouteRepository;

@Service
public class BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;

    public BusRoute addBusRoute(BusRoute busRoute) {
        return busRouteRepository.save(busRoute);
    }

    public BusRoute updateBusRoute(Long id, BusRoute busRoute) {
        if (busRouteRepository.existsById(id)) {
            busRoute.setBusNumber(id);
            return busRouteRepository.save(busRoute);
        }
        return null;
    }

    public void deleteBusRoute(Long id) {
        busRouteRepository.deleteById(id);
    }
}
