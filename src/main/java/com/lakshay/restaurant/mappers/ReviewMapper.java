package com.lakshay.restaurant.mappers;

import com.lakshay.restaurant.domain.ReviewCreateUpdateRequest;
import com.lakshay.restaurant.domain.dtos.ReviewCreateUpdateRequestDto;
import com.lakshay.restaurant.domain.dtos.ReviewDto;
import com.lakshay.restaurant.domain.entities.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewCreateUpdateRequest toReviewCreateUpdateRequest(ReviewCreateUpdateRequestDto dto);
    ReviewDto toReviewDto(Review review);
}
