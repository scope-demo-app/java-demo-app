package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.exception.RestaurantAlreadyDeletedException;
import com.undefinedlabs.scope.exception.RestaurantNotFoundException;
import com.undefinedlabs.scope.exception.WrongArgumentException;
import com.undefinedlabs.scope.model.Geoposition;
import com.undefinedlabs.scope.model.Restaurant;
import com.undefinedlabs.scope.repository.RestaurantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

@Service
public class RestaurantService {


    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantService.class);
    private RestaurantRepository repository;
    private GeopositionService geopositionService;


    @Autowired
    public RestaurantService(final RestaurantRepository repository, final GeopositionService geopositionService) {
        this.repository = repository;
        this.geopositionService = geopositionService;
    }

    public List<Restaurant> getAllRestaurants() {
        return this.repository.findAll();
    }

    public Restaurant getRestaurantById(@NotNull final String id) {
        LOGGER.debug("Finding restaurant by id: " + id);
        if (StringUtils.isEmpty(id)) {
            throw new WrongArgumentException("id cannot be empty");
        }

        if(Restaurant.FLAKY_RESTAURANT_ID.equalsIgnoreCase(id)){
            final int randInt = ThreadLocalRandom.current().nextInt(12);
            if(randInt > 5){
                throw new RuntimeException(new TimeoutException("Timeout!!"));
            }

            return Restaurant.DUMMY;

        } else {
            try {
                final Restaurant restaurant = this.repository.findById(UUID.fromString(id)).orElseThrow(() -> new RestaurantNotFoundException("Restaurant " + id + " not found"));
                LOGGER.debug("Found restaurant: "+restaurant);
                return restaurant;
            } catch (final IllegalArgumentException e) {
                throw new WrongArgumentException(e.getMessage());
            }
        }
    }

    public List<Restaurant> getRestaurantsByName(@NotNull final String name) {
        LOGGER.debug("Finding restaurant by name: " + name);
        if (StringUtils.isEmpty(name)) {
            throw new WrongArgumentException("name cannot be empty");
        }

        try {
            final List<Restaurant> restauransByName = this.repository.findByNameContains(name);
            LOGGER.debug("Found restaurants by name: "+restauransByName+"");
            return restauransByName;
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        }
    }

    public Restaurant createRestaurant(@NotNull final Restaurant restaurant) {
        LOGGER.debug("Creating new restaurant: " + restaurant);
        if (restaurant == null) {
            throw new WrongArgumentException("restaurant cannot be null");
        }

        if (StringUtils.isEmpty(restaurant.getLatitude()) || StringUtils.isEmpty(restaurant.getLongitude())){
            final Geoposition geoposition = this.geopositionService.getGeoposition(restaurant);
            restaurant.setLatitude(geoposition.getLatitude());
            restaurant.setLongitude(geoposition.getLongitude());
        }

        final Restaurant createdRestaurant = this.repository.save(restaurant);
        LOGGER.debug("Created new restaurant with id: " + createdRestaurant.getId());
        return createdRestaurant;
    }

    public Restaurant updateRestaurant(@NotNull final String id, @NotNull final Restaurant restaurant) {
        LOGGER.debug("Updating restaurant by id: " + id + " changing to: " + restaurant);
        if (StringUtils.isEmpty(id)) {
            throw new WrongArgumentException("id cannot be empty");
        }

        if (restaurant == null) {
            throw new WrongArgumentException("restaurant cannot be null");
        }


        try {
            final Restaurant toUpdate = this.repository.getOne(UUID.fromString(id));

            toUpdate.setName(restaurant.getName());
            toUpdate.setDescription(restaurant.getDescription());
            toUpdate.setLatitude(restaurant.getLatitude());
            toUpdate.setLongitude(restaurant.getLongitude());
            final Restaurant updatedRestaurant = this.repository.save(toUpdate);
            LOGGER.debug("Updated restaurant.");
            return updatedRestaurant;
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        }
    }

    public void deleteRestaurant(@NotNull final String id) {
        LOGGER.debug("Deleting restaurant by id: " + id);
        if (StringUtils.isEmpty(id)) {
            throw new WrongArgumentException("id cannot empty");
        }

        try {
            this.repository.deleteById(UUID.fromString(id));
            LOGGER.info("Deleted restaurant.");
        } catch (IllegalArgumentException e) {
            throw new WrongArgumentException(e.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new RestaurantAlreadyDeletedException(e.getMessage());
        }
    }
}
