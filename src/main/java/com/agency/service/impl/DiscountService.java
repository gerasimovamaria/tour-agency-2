package com.agency.service.impl;

import com.agency.dto.request.DiscountRequest;
import com.agency.exception.ConflictException;
import com.agency.exception.NotFoundException;
import com.agency.model.Discount;
import com.agency.model.Tour;
import com.agency.model.User;
import com.agency.repository.DiscountRepository;
import com.agency.repository.TourRepository;
import com.agency.repository.UserRepository;
import com.agency.service.IDiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DiscountService implements IDiscountService {

    private DiscountRepository discountRepository;
    private TourRepository tourRepository;
    private UserRepository userRepository;

    @Override
    @Transactional
    public void save(DiscountRequest discountRequest) {
        Optional<User> user = userRepository.findById(discountRequest.getUserId());
        Optional<Tour> tour = tourRepository.findById(discountRequest.getTourId());

        log.info("save(DiscountRequest discountRequest = {})", discountRequest);

        if (user.isPresent() && tour.isPresent()) {
            log.info("Discount saving process starts");

            Discount discountToSave = Discount.builder()
                    .user(user.get())
                    .tour(tour.get())
                    .isPercent(discountRequest.getIsPercent())
                    .amount(discountRequest.getAmount())
                    .build();

            discountRepository.save(discountToSave);
        } else {
            log.error("User with id {} or Tour with id = {} doesn't exist", discountRequest.getUserId(), discountRequest.getTourId());
            throw new NotFoundException("User with id = " + discountRequest.getUserId() + "or Tour with id = " + discountRequest.getTourId() + " doesn't exist");
        }
    }

    @Override
    @Transactional
    public void update(DiscountRequest discountRequest) {
        Optional<User> user = userRepository.findById(discountRequest.getUserId());
        Optional<Tour> tour = tourRepository.findById(discountRequest.getTourId());
        Optional<Discount> optDiscount = discountRepository.findById(discountRequest.getId());

        log.info("update(DiscountRequest discountRequest = {})", discountRequest);

        if (!optDiscount.isPresent()) {
            log.error("Discount with id = {} doesn't exist", discountRequest.getId());
            throw new NotFoundException("Discount with id = " + discountRequest.getId() + " doesn't exist");
        } else if (user.isPresent() && tour.isPresent()) {
            log.info("Discount updating process starts");

            Discount discount = optDiscount.get();

            discount.setUser(user.get());
            discount.setTour(tour.get());
            discount.setIsPercent(discountRequest.getIsPercent());
            discount.setAmount(discountRequest.getAmount());

            discountRepository.save(discount);
        } else {
            Long userId = discountRequest.getUserId();
            Long tourId = discountRequest.getTourId();

            log.error("user id = {} or tour id = {} doesn't exist", userId, tourId);
            throw new NotFoundException("User id = " + userId + " or tour id = " + tourId + " for discount updating don't exist");
        }
    }

    @Override
    @Transactional
    public List<Discount> getAll() {
        log.debug("Get all the discounts");
        return discountRepository.findAll();
    }

    @Override
    @Transactional
    public List<Discount> getByUserId(Long id) {
        if (id <= 0) {
            log.error("getByUserId(Long id = {})", id);
            throw new IllegalArgumentException("Id " + id + " is less than zero");
        }

        return discountRepository.findByUserId(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("deleteById(Long id = {})", id);

        Optional<Discount> optionalDiscount = discountRepository.findById(id);

        if (id <= 0) {
            log.error("Id = {} less than 0", id);
            throw new IllegalArgumentException("Id " + id + " is less than zero");
        } else if (!discountRepository.findById(id).isPresent()) {
            log.error("Object with id = {} doesn't exist");
            throw new NotFoundException("Discount with id " + id + " doesn't exist");
        } else if (optionalDiscount.isPresent() && optionalDiscount.get().getUser() != null) {
            Long discountId = optionalDiscount.get().getId();
            Long userId = optionalDiscount.get().getUser().getId();

            log.error("Discount with id = {} refers to the user with id = {} and can't be deleted", discountId, userId);
            throw new ConflictException("The discount with id = " + discountId + " can't be deleted because user with id = " + userId + " refers to this discount");
        }

        discountRepository.deleteById(id);
    }

    @Autowired
    public DiscountService(DiscountRepository discountRepository, TourRepository tourRepository, UserRepository userRepository) {
        this.discountRepository = discountRepository;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
    }
}
