package com.agency.controller;

import com.agency.dto.request.DiscountRequest;
import com.agency.model.Discount;
import com.agency.service.IDiscountService;
import com.agency.service.impl.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {

    private IDiscountService discountService;

    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<Discount> getAll() {
        return discountService.getAll();
    }

    @GetMapping("{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Discount> getByUserId(@PathVariable Long userId) {
        return discountService.getByUserId(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void save(@RequestBody @Valid DiscountRequest discountRequest) {
        discountService.save(discountRequest);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public void update(@RequestBody @Valid DiscountRequest discountRequest) {
        discountService.update(discountRequest);
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("hasRole('USER')")
    public void delete(@PathVariable Long userId) {
        discountService.deleteById(userId);
    }

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
}
