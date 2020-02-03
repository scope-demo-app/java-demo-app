package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.exception.RestaurantNotFoundException;
import com.undefinedlabs.scope.model.Restaurant;
import com.undefinedlabs.scope.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantService(final RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAllRestaurants() {
        return this.repository.findAll();
    }

    public Restaurant getRestaurantById(final String id){
        return this.repository.findById(UUID.fromString(id)).orElseThrow(() -> new RestaurantNotFoundException("Restaurant " + id + " not found"));
    }

    public List<Restaurant> getRestaurantsByName(final String name) {
        return this.repository.findByNameContains(name);
    }

    public Restaurant createRestaurant(final Restaurant restaurant) {
        return this.repository.save(restaurant);
    }

    public Restaurant updateRestaurant(final String id, final Restaurant restaurant) {
        final Restaurant toUpdate = this.repository.getOne(UUID.fromString(id));

        toUpdate.setName(restaurant.getName());
        toUpdate.setDescription(restaurant.getDescription());
        toUpdate.setLatitude(restaurant.getLatitude());
        toUpdate.setLongitude(restaurant.getLongitude());
        return this.repository.save(toUpdate);
    }

    public void deleteRestaurant(final String id) {
        this.repository.deleteById(UUID.fromString(id));
    }
}
