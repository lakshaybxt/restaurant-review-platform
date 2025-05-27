package com.lakshay.restaurant.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lakshay.restaurant.domain.GeoLocation;
import com.lakshay.restaurant.domain.entities.Address;
import com.lakshay.restaurant.exceptions.GeoLocationException;
import com.lakshay.restaurant.services.GeoLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class GeoLocationServiceImpl implements GeoLocationService {

    private final RestTemplate restTemplate;

    @Override
    public GeoLocation geoLocate(Address address) {
        try {
            String fullAddress = String.format("%s %s %s %s %s %s %s",
                    address.getStreetNumber(),
                    address.getStreetName(),
                    address.getUnit(),
                    address.getCity(),
                    address.getState(),
                    address.getPostalCode(),
                    address.getCountry()
            ).replaceAll("null", "").replaceAll("\\s{2,}", " ").trim();

            // URLEncoder.encode() converts the string into a safe URL format.
            String encodedAddress = URLEncoder.encode(fullAddress, StandardCharsets.UTF_8);

            // Build the API Request URL
            String url = "https://nominatim.openstreetmap.org/search?q=" + encodedAddress + "&format=json&limit=1";

            // Nominatim requires a User-Agent header, otherwise it may reject the request.
            //Pretend like we’re a real app.
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "SpringBootApp/1.0");

            // Make the API Call
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, String.class
            );

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            if(root.isArray() && root.size() > 0) {
                JsonNode location = root.get(0);
                double lat = location.get("lat").asDouble();
                double lon = location.get("lon").asDouble();
                return GeoLocation.builder()
                        .latitude(lat)
                        .longitude(lon)
                        .build();
            }
        } catch (Exception e) {
            throw new GeoLocationException("Failed to retrieve geolocation", e);
        }
        return null;
    }
}
