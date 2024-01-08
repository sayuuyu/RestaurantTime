package outils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    Carte emptyCarte, singleItemCarte, multipleItemsCarte;

    @BeforeEach
    void setUp() {
        emptyCarte = new Carte(new String[0][0], 0);

        String[][] singleItem = {{"Starter", "Salad", "5.99"}};
        singleItemCarte = new Carte(singleItem, 1);

        String[][] multipleItems = {{"Starter", "Salad", "5.99"}, {"Main", "Steak", "15.99"}};
        multipleItemsCarte = new Carte(multipleItems, 2);
    }

    @Test
    void afficherCarte() {
        assertEquals("", emptyCarte.afficherCarte(), "Empty carte should return an empty string");

        assertEquals("Starter\n -Salad  5.99\n", singleItemCarte.afficherCarte(), "Single item carte display mismatch");

        assertEquals("Starter\n -Salad  5.99\nMain\n -Steak  15.99\n", multipleItemsCarte.afficherCarte(), "Multiple items carte display mismatch");
    }

    @Test
    void addElementDansCarte() {
        String[] newElement = {"Dessert", "Cake", "3.99"};
        assertTrue(emptyCarte.addElementDansCarte(newElement), "Should be able to add to empty carte");
        assertEquals("Dessert\n -Cake  3.99\n", emptyCarte.afficherCarte(), "Adding to empty carte failed");

        assertTrue(singleItemCarte.addElementDansCarte(newElement), "Should be able to add new item to single item carte");
        assertEquals("Starter\n -Salad  5.99\nDessert\n -Cake  3.99\n", singleItemCarte.afficherCarte(), "Adding to single item carte failed");

        assertTrue(multipleItemsCarte.addElementDansCarte(newElement), "Should be able to add new item to multiple items carte");
        assertEquals("Starter\n -Salad  5.99\nMain\n -Steak  15.99\nDessert\n -Cake  3.99\n", multipleItemsCarte.afficherCarte(), "Adding to multiple items carte failed");

        assertFalse(multipleItemsCarte.addElementDansCarte(newElement), "Should not add duplicate item");
    }

    @Test
    void getIElement() {
        assertEquals(0, singleItemCarte.getIElement("Salad"), "Index of existing element 'Salad' should be 0");
        assertEquals(1, multipleItemsCarte.getIElement("Steak"), "Index of existing element 'Steak' should be 1");
        assertEquals(2, multipleItemsCarte.getIElement("NonExisting"), "Index of non-existing element should be equal to the number of elements");
    }

    @Test
    void delElementDansCarte() {
        multipleItemsCarte.delElementDansCarte(0);
        assertEquals("Main\n -Steak  15.99\n", multipleItemsCarte.afficherCarte(), "Deletion of the first element failed");

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            emptyCarte.delElementDansCarte(0);
        }, "Deletion from an empty carte should throw an exception");
    }

    @Test
    void changeElementDansCarte() {
        String[] changedElement = {"Main", "Fish", "12.99"};
        singleItemCarte.changeElementDansCarte(0, changedElement);
        assertEquals("Main\n -Fish  12.99\n", singleItemCarte.afficherCarte(), "Changing element in single item carte failed");

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            emptyCarte.changeElementDansCarte(0, changedElement);
        }, "Changing element in an empty carte should throw an exception");
    }
}