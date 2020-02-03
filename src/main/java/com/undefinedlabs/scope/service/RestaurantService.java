package com.undefinedlabs.scope.service;

import com.undefinedlabs.scope.exception.RestaurantNotFoundException;
import com.undefinedlabs.scope.model.Restaurant;
import com.undefinedlabs.scope.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
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

    public Restaurant getRestaurantById(@NotNull final String id){
        if (StringUtils.isEmpty(id)){
            throw new IllegalArgumentException("id cannot be empty");
        }

        return this.repository.findById(UUID.fromString(id)).orElseThrow(() -> new RestaurantNotFoundException("Restaurant " + id + " not found"));
    }

    public List<Restaurant> getRestaurantsByName(@NotNull  final String name) {
        if(StringUtils.isEmpty(name)){
            throw new IllegalArgumentException("name cannot be empty");
        }

        return this.repository.findByNameContains(name);
    }

    public Restaurant createRestaurant(@NotNull final Restaurant restaurant) {
        if(restaurant == null) {
            throw new IllegalArgumentException("restaurant cannot be null");
        }

        return this.repository.save(restaurant);
    }

    public Restaurant updateRestaurant(@NotNull final String id, @NotNull final Restaurant restaurant) {
        if(StringUtils.isEmpty(id)) {
            throw new IllegalArgumentException("id cannot be empty");
        }

        if(restaurant == null) {
            throw new IllegalArgumentException("restaurant cannot be null");
        }


        final Restaurant toUpdate = this.repository.getOne(UUID.fromString(id));

        toUpdate.setName(restaurant.getName());
        toUpdate.setDescription(restaurant.getDescription());
        toUpdate.setLatitude(restaurant.getLatitude());
        toUpdate.setLongitude(restaurant.getLongitude());
        return this.repository.save(toUpdate);
    }

    public void deleteRestaurant(@NotNull final String id) {
        if(StringUtils.isEmpty(id)){
            throw new IllegalArgumentException("id cannot empty");
        }

        this.repository.deleteById(UUID.fromString(id));
    }
}
