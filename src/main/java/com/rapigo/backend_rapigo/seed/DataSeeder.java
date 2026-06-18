package com.rapigo.backend_rapigo.seed;

import com.rapigo.backend_rapigo.model.Coupon;
import com.rapigo.backend_rapigo.model.MenuItem;
import com.rapigo.backend_rapigo.model.Restaurant;
import com.rapigo.backend_rapigo.repository.CouponRepository;
import com.rapigo.backend_rapigo.repository.MenuItemRepository;
import com.rapigo.backend_rapigo.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final CouponRepository couponRepository;

    public DataSeeder(RestaurantRepository restaurantRepository,
                      MenuItemRepository menuItemRepository,
                      CouponRepository couponRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.couponRepository = couponRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (restaurantRepository.count() == 0) {
            Restaurant r1 = restaurantRepository.save(new Restaurant(
                    "Pizzería Napoli", "Pizza",
                    "https://images.unsplash.com/photo-1513104890138-7c749659a591"));
            Restaurant r2 = restaurantRepository.save(new Restaurant(
                    "Sushi Zen", "Sushi",
                    "https://images.unsplash.com/photo-1579871494447-9811cf80d66c"));
            Restaurant r3 = restaurantRepository.save(new Restaurant(
                    "Burger House", "Burgers",
                    "https://images.unsplash.com/photo-1568901346375-23c9450c58cd"));

            // Restaurant 1 — id=1; first item (Margherita) will be id=1
            menuItemRepository.save(new MenuItem(r1.getId(), "Margherita", 50.00));
            menuItemRepository.save(new MenuItem(r1.getId(), "Pepperoni", 60.00));
            menuItemRepository.save(new MenuItem(r1.getId(), "Hawaiana", 40.00));

            // Restaurant 2
            menuItemRepository.save(new MenuItem(r2.getId(), "California Roll", 30.00));
            menuItemRepository.save(new MenuItem(r2.getId(), "Sake Nigiri", 45.00));
            menuItemRepository.save(new MenuItem(r2.getId(), "Tempura Roll", 35.00));

            // Restaurant 3
            menuItemRepository.save(new MenuItem(r3.getId(), "Clásica", 25.00));
            menuItemRepository.save(new MenuItem(r3.getId(), "Doble Carne", 35.00));
            menuItemRepository.save(new MenuItem(r3.getId(), "BBQ Bacon", 30.00));

            couponRepository.save(new Coupon("DELI10", 10));
        }
    }
}
