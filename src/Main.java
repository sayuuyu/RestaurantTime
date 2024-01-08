import boundary.MenuRestaurant;
import control.ControlAnnulation;
import control.ControlReservation;
import control.ControlRestaurant;
import outils.Carte;
import restaurant.RestaurantALaCarte;

import javax.swing.*;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int[] test = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            String[][] test3 = new String[10][10];
            test3[0][0] = "test1";
            test3[0][1] = "test1";
            test3[0][2] = "test1";
            test3[1][0] = "test2";
            test3[1][1] = "test2";
            test3[1][2] = "test2";
            Carte carte = new Carte(test3, 2);
            Calendar[][] calendar = new Calendar[7][10];
            calendar[1][0] = Calendar.getInstance();
            Calendar hour = Calendar.getInstance();
            hour.add(Calendar.HOUR, 1);
            calendar[1][1] = hour;
            RestaurantALaCarte restaurant = new RestaurantALaCarte("Test", carte, "local", "desc", calendar, test, 1, 10);
            ControlRestaurant controlRestaurant = new ControlRestaurant(restaurant);
            ControlReservation<RestaurantALaCarte> controlReservation = new ControlReservation<>(restaurant);
            MenuRestaurant menu = new MenuRestaurant(controlReservation, controlRestaurant, new ControlAnnulation<>(restaurant));
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(menu.$$$getRootComponent$$$());
            frame.setSize(720, 480);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

