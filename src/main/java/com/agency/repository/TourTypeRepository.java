package com.agency.repository;

import com.agency.model.TourType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface TourTypeRepository extends JpaRepository<TourType, Long> {

    Optional<TourType> getByTourTypeName(String tourName);
}