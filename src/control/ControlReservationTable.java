package control;

import outils.Table;
import restaurant.Restaurant;
import restaurant.RestaurantALaCarte;

public class ControlReservationTable extends ControlReservation {
    private RestaurantALaCarte restaurantTab;

    public ControlReservationTable(RestaurantALaCarte restaurantALaCarte) {
        super(restaurantALaCarte);
        this.restaurantTab = restaurantALaCarte;
    }

    public int[] getIndiceTablesLibres(int[] data){
        return this.restaurantTab.tablesLibres(data[1],data[0]);
    }

    public String[] getTablesLibres(int[] data){
        int[] indicesTab = this.getIndiceTablesLibres(data);
        int taille = indicesTab.length;
        String[] tables =new String[taille];
        StringBuilder laTab = new StringBuilder("Table n°");
        for(int j = 0; j<taille;j++){
            laTab = new StringBuilder("Table n°");
            laTab.append(indicesTab[j]);
            tables[j]=laTab.toString();
        }
        return tables;
    }
}
