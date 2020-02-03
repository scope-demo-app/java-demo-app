package com.undefinedlabs.scope.controller;

import com.undefinedlabs.scope.model.Restaurant;
import com.undefinedlabs.scope.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService service;

    @Autowired
    public RestaurantController(final RestaurantService service) {
        this.service = service;
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public List<Restaurant> getAllRestaurants(@RequestParam(name = "name") final Optional<String> name) {
        return name.isPresent() ? this.service.getRestaurantsByName(name.get()) : this.service.getAllRestaurants();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Restaurant getRestaurantById(@PathVariable(name = "id") final String id) {
        return this.service.getRestaurantById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody final Restaurant restaurant) {
        return this.service.createRestaurant(restaurant);
    }

    @PatchMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Restaurant updateRestaurant(@PathVariable(name = "id") final String id, @RequestBody final Restaurant restaurant) {
        return this.service.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable(name = "id") final String id) {
        this.service.deleteRestaurant(id);
    }

}
