package com.agency.repository;

import com.agency.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface TourRepository extends JpaRepository<Tour, Long> {

    Optional<Tour> findById(Long id);

    @Modifying
    @Query("update Tour t set t.buyer.id =:userId where t.id =:tourId")
    void buyTour(@Param("userId") Long userId, @Param("tourId") Long tourId);

    @Modifying
    @Query("update Tour t set t.isHot =:isHot where t.id =:tourId")
    void setHotStatus(@Param("isHot") Boolean isHot, @Param("tourId") Long tourId);
}
