package com.agency.controller;

import com.agency.dto.request.TourBuyRequest;
import com.agency.dto.request.TourRequest;
import com.agency.dto.request.TourStatusHotRequest;
import com.agency.model.Tour;
import com.agency.security.CurrentUser;
import com.agency.security.UserPrincipal;
import com.agency.service.ITourService;
import com.agency.service.impl.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tour")
public class TourController {

    private ITourService tourService;

    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<Tour> getAll() {
        return tourService.all();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public Tour findById(@PathVariable Long id) {
        return tourService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void saveTour(@RequestBody @Valid TourRequest tourRequest) {
        tourService.save(tourRequest);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteById(id);
    }

    @PutMapping("buy")
    @PreAuthorize("hasRole('USER')")
    public void buyTour(@RequestBody @Valid TourBuyRequest tour, @CurrentUser UserPrincipal currentUser) {
        tourService.buy(tour, currentUser);
    }

    @PutMapping("hot-status")
    @PreAuthorize("hasRole('USER')")
    public void setHotStatus(@RequestBody @Valid TourStatusHotRequest tour) {
        tourService.setHotStatus(tour);
    }

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }
}
