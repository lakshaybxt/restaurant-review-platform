package com.lakshay.restaurant.services;

import com.lakshay.restaurant.domain.GeoLocation;
import com.lakshay.restaurant.domain.entities.Address;

public interface GeoLocationService {
    GeoLocation geoLocate(Address address);
}
