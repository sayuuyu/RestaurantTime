package control;

import restaurant.Restaurant;

public class ControlAnnulation<T extends Restaurant> {
    private final T restaurant;

    public ControlAnnulation(T restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean annuler(String code) {
        return this.restaurant.annuler(code);
    }

}
