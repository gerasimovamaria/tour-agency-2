package com.agency.service.impl;

import com.agency.dto.request.TourBuyRequest;
import com.agency.dto.request.TourRequest;
import com.agency.dto.request.TourStatusHotRequest;
import com.agency.exception.NotFoundException;
import com.agency.model.Tour;
import com.agency.repository.RouteRepository;
import com.agency.repository.TourRepository;
import com.agency.repository.TourTypeRepository;
import com.agency.security.UserPrincipal;
import com.agency.service.ITourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TourService implements ITourService {

    private TourRepository tourRepository;
    private RouteRepository routeRepository;
    private TourTypeRepository tourTypeRepository;

    @Override
    @Transactional
    public List<Tour> all() {
        log.debug("Get all the tours");
        return tourRepository.findAll();
    }

    @Transactional
    @Override
    public Tour getById(Long id) {
        log.error("getById(Long id = {})", id);
        return tourRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Tour with id " + id + " wasn't found")
        );
    }

    @Transactional
    @Override
    public void buy(TourBuyRequest tour, UserPrincipal currentUser) {
        log.info("buy(TourBuyRequest tour = {}, UserPrincipal currentUser = {})", tour, currentUser);
        tourRepository.buyTour(currentUser.getId(), tour.getTourId());
    }

    @Transactional
    @Override
    public void setHotStatus(TourStatusHotRequest tour) {
        tourRepository.setHotStatus(tour.getIsHot(), tour.getTourId());
    }

    @Override
    @Transactional
    public void save(TourRequest tourRequest) {
        if (!routeRepository.getByDeptPlaceAndDestPlace(tourRequest.getDeparturePlace(), tourRequest.getDestinationPlace()).isPresent()) {
            log.error("Route {} doesn't exist", tourRequest);
            throw new NotFoundException("Route wasn't found");
        } else if (!tourTypeRepository.getByTourTypeName(tourRequest.getTourType()).isPresent()) {
            log.error("TourType {} doesn't exist", tourRequest.getTourType());
            throw new NotFoundException("Tour type " + tourRequest.getTourType() + " wasn't found");
        }

        Tour tour = Tour.builder()
                .tourType(tourTypeRepository.getByTourTypeName(tourRequest.getTourType().toUpperCase()).get())
                .places(routeRepository.getByDeptPlaceAndDestPlace(tourRequest.getDeparturePlace(), tourRequest.getDestinationPlace()).get())
                .name(tourRequest.getName())
                .price(tourRequest.getPrice())
                .build();

        tourRepository.save(tour);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.error("deleteById(Long id = {})", id);

        if (id <= 0) {
            log.error("Id = {} less than 0", id);
            throw new IllegalArgumentException("Id " + id + " is less than zero");
        } else if (!tourRepository.findById(id).isPresent()) {
            log.error("Object with id = {} doesn't exist");
            throw new NotFoundException("Tour with id " + id + " doesn't exist");
        }

        tourRepository.deleteById(id);
    }

    @Autowired
    public TourService(TourRepository tourRepository, RouteRepository routeRepository, TourTypeRepository tourTypeRepository) {
        this.tourRepository = tourRepository;
        this.routeRepository = routeRepository;
        this.tourTypeRepository = tourTypeRepository;
    }
}
