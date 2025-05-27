package com.lakshay.restaurant.mappers;

import com.lakshay.restaurant.domain.dtos.PhotoDto;
import com.lakshay.restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
}
