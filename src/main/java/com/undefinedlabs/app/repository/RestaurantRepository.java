package com.undefinedlabs.app.repository;

import com.undefinedlabs.app.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    List<Restaurant> findByNameContains(String name);
}
