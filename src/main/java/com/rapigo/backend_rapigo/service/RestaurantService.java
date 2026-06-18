package com.rapigo.backend_rapigo.service;

import com.rapigo.backend_rapigo.model.MenuItem;
import com.rapigo.backend_rapigo.model.Restaurant;
import com.rapigo.backend_rapigo.repository.MenuItemRepository;
import com.rapigo.backend_rapigo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> list(String cuisine) {
        if (cuisine == null || cuisine.isBlank()) return restaurantRepository.findAll();
        return restaurantRepository.findAll().stream()
                .filter(r -> r.getCuisine().equalsIgnoreCase(cuisine)) // BUG RG-207: usa equals (sensible a mayúsculas) en vez de equalsIgnoreCase
                .toList();
    }

    public List<MenuItem> menu(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
}
