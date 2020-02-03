package com.undefinedlabs.scope.controller;

import com.undefinedlabs.scope.model.Restaurant;
import com.undefinedlabs.scope.service.RestaurantService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Restaurant> getAllRestaurants(@RequestParam(name = "name") final Optional<String> name) {
        return name.isPresent() ? this.service.getRestaurantsByName(name.get()) : this.service.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable(name = "id") final String id) {
        return this.service.getRestaurantById(id);
    }

}
