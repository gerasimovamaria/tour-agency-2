package com.agency.repository;

import com.agency.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findByUserId(Long id);

    void deleteById(Long id);
}
