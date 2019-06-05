package com.agency.repository;

import com.agency.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> getByDeptPlaceAndDestPlace(String deptPlace, String destPlace);
}
