package com.lakshay.restaurant.controllers;

import com.lakshay.restaurant.domain.ReviewCreateUpdateRequest;
import com.lakshay.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.lakshay.restaurant.domain.dtos.ReviewDto;
import com.lakshay.restaurant.domain.entities.Review;
import com.lakshay.restaurant.domain.entities.User;
import com.lakshay.restaurant.mappers.ReviewMapper;
import com.lakshay.restaurant.services.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/restaurants/{restaurantId}/reviews")
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(
            @PathVariable String restaurantId,
            @Valid @RequestBody ReviewCreateUpdateRequestDto review,
            @AuthenticationPrincipal Jwt jwt) {
        ReviewCreateUpdateRequest reviewCreateUpdateRequest = reviewMapper.toReviewCreateUpdateRequest(review);

        User user = jwtToUser(jwt);

        Review createdReview =  reviewService.createReview(user, restaurantId, reviewCreateUpdateRequest);

        return ResponseEntity.ok(reviewMapper.toReviewDto(createdReview));
    }

    private User jwtToUser(Jwt jwt) {
        return User.builder()
                .id(jwt.getSubject())
                .username(jwt.getClaimAsString("preferred_username"))
                .givenName(jwt.getClaimAsString("given_name"))
                .familyName(jwt.getClaimAsString("family_name"))
                .build();
    }
}
