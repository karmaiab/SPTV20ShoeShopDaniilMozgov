/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Buyer;
import entity.History;
import entity.Model;
import entity.Shop;
import java.util.List;

/**
 *
 * @author User
 */
public interface Keeping {
    public void saveModels (List<Model> models);
    public List<Model> loadModels();
    public void saveBuyers (List<Buyer> buyers);
    public List<Buyer> loadBuyers();
    public void saveHistories (List<History> histories);
    public List<History> loadHistories();
    public void saveShops (List<Shop> shops);
    public List<Shop> loadShops();
}
