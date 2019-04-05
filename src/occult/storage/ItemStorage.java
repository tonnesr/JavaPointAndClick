/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occult.storage;

/**
 *
 * @author Tonnes
 */
public class ItemStorage {
    String[][] items;
    
    public ItemStorage() {
        items = new String[][] {{"magnifiying glass", "Magnifiying Glass_"},
                                {"Book", "Book_"}, 
                                {"Glass", "Glass_"}};
    }
    
    public String[][] getArray() {
        return items;
    }
}
