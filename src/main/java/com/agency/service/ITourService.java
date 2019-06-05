package com.agency.service;

import com.agency.dto.request.TourBuyRequest;
import com.agency.dto.request.TourRequest;
import com.agency.dto.request.TourStatusHotRequest;
import com.agency.model.Tour;
import com.agency.security.UserPrincipal;

import java.util.List;


public interface ITourService {

    List<Tour> all();

    Tour getById(Long id);

    void buy(TourBuyRequest tour, UserPrincipal currentUser);

    void setHotStatus(TourStatusHotRequest tour);

    void save(TourRequest tourRequest);

    void deleteById(Long id);
}
