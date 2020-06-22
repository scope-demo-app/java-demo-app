package com.undefinedlabs.app.controller;

import com.undefinedlabs.app.model.Restaurant;
import com.undefinedlabs.app.service.RestaurantService;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);
    private RestaurantService service;

    @Autowired
    public RestaurantController(final RestaurantService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Restaurant> getAllRestaurants(@RequestParam(name = "name", required = false) final String name) {
        LOGGER.info("getAllRestaurants " + (StringUtils.isNotEmpty(name) ? " by name: " + name : ""));
        return StringUtils.isNotEmpty(name) ? this.service.getRestaurantsByName(name) : this.service.getAllRestaurants();
    }

    @GetMapping(value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Restaurant getRestaurantById(@PathVariable(name = "id") final String id) {
        LOGGER.info("getRestaurantById: " + id);
        return this.service.getRestaurantById(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody final Restaurant restaurant) {
        LOGGER.info("createRestaurant: " + restaurant);
        return this.service.createRestaurant(restaurant);
    }

    @PatchMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Restaurant updateRestaurant(@PathVariable(name = "id") final String id, @RequestBody final Restaurant restaurant) {
        LOGGER.info("updateRestaurant: id: " + id + ", new restaurant: " + restaurant);
        return this.service.updateRestaurant(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable(name = "id") final String id) {
        LOGGER.info("deleteRestaurant: id: " + id);
        this.service.deleteRestaurant(id);
    }

}
