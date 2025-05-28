package com.lakshay.restaurant.services;

import com.lakshay.restaurant.domain.ReviewCreateUpdateRequest;
import com.lakshay.restaurant.domain.entities.Review;
import com.lakshay.restaurant.domain.entities.User;

public interface ReviewService {
    Review createReview(User author, String restaurantId, ReviewCreateUpdateRequest review);
}
