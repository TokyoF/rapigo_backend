package com.rapigo.backend_rapigo.controller;

import com.rapigo.backend_rapigo.model.MenuItem;
import com.rapigo.backend_rapigo.model.Restaurant;
import com.rapigo.backend_rapigo.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public List<Restaurant> list(@RequestParam(required = false) String cuisine) {
        return restaurantService.list(cuisine);
    }

    @GetMapping("/restaurants/{id}/menu")
    public List<MenuItem> menu(@PathVariable Long id) {
        return restaurantService.menu(id);
    }
}
