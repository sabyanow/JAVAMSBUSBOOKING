package com.springboot.adminms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.adminms.model.BusRoute;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
}
