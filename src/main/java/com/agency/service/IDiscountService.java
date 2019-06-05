package com.agency.service;

import com.agency.dto.request.DiscountRequest;
import com.agency.model.Discount;

import java.util.List;
import java.util.Optional;


public interface IDiscountService {

    void save(DiscountRequest discountRequest);

    void update(DiscountRequest discountRequest);

    List<Discount> getAll();

    List<Discount> getByUserId(Long id);

    void deleteById(Long id);
}
