package com.lakshay.restaurant.controllers;

import com.lakshay.restaurant.domain.RestaurantCreateUpdateRequest;
import com.lakshay.restaurant.domain.dtos.RestaurantCreateUpdateRequestDto;
import com.lakshay.restaurant.domain.dtos.RestaurantDto;
import com.lakshay.restaurant.domain.dtos.RestaurantSummaryDto;
import com.lakshay.restaurant.domain.entities.Restaurant;
import com.lakshay.restaurant.mappers.RestaurantMapper;
import com.lakshay.restaurant.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(
            @Valid @RequestBody RestaurantCreateUpdateRequestDto request) {
        RestaurantCreateUpdateRequest restaurantCreateUpdateRequest = restaurantMapper
                .toRestaurantCreateUpdateRequest(request);

        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurantCreateUpdateRequest);
        RestaurantDto createdRestaurantDto = restaurantMapper.toRestaurantDto(createdRestaurant);

        return ResponseEntity.ok(createdRestaurantDto);
    }

    @GetMapping
    public Page<RestaurantSummaryDto> searchRestaurant(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Float minRating,
            @RequestParam(required = false) Float latitude,
            @RequestParam(required = false) Float longitude,
            @RequestParam(required = false) Float radius,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        Page<Restaurant> searchResults = restaurantService.searchRestaurants(
                q, minRating, latitude, longitude, radius, PageRequest.of(page - 1, size)
        );

        return searchResults.map(restaurantMapper::toSummaryDto);
    }
}
