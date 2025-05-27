package com.lakshay.restaurant.repository;

import com.lakshay.restaurant.domain.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {

    // Find restaurants with averageRating >= minRating
    Page<Restaurant> findByAverageRatingGreaterThanEqual(Float minRating, Pageable pageable);

    // Search by query string (name or cuisineType, fuzzy match) and minimum rating filter
    @Query("{" +
            "  \"bool\": {" +
            "    \"must\": [" +
            "      { \"range\": { \"averageRating\": { \"gte\": ?1 } } }" +
            "    ]," +
            "    \"should\": [" +
            "      { \"fuzzy\": { \"name\": { \"value\": \"?0\", \"fuzziness\": \"AUTO\" } } }," +
            "      { \"fuzzy\": { \"cuisineType\": { \"value\": \"?0\", \"fuzziness\": \"AUTO\" } } }" +
            "    ]," +
            "    \"minimum_should_match\": 1" +
            "  }" +
            "}")
    Page<Restaurant> findByQueryAndMinRating(String query, Float minRating, Pageable pageable);

    // Find restaurants near a geolocation within a given radius (distance must be a string like "10km")
    @Query("{" +
            "  \"bool\": {" +
            "    \"must\": [" +
            "      { \"geo_distance\": {" +
            "          \"distance\": \"?2km\"," +
            "          \"geoLocation\": {" +
            "            \"lat\": ?0," +
            "            \"lon\": ?1" +
            "           }" +
            "        } }" +
            "    ]" +
            "  }" +
            "}")
    Page<Restaurant> findByLocationNear(Float latitude, Float longitude, Float radiusKm, Pageable pageable);

}
