/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
public class History implements Serializable{
    private Model model;
    private Buyer buyer;
    private Date soldShoes;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Date getSoldShoes() {
        return soldShoes;
    }

    public void setSoldShoes(Date soldShoes) {
        this.soldShoes = soldShoes;
    }
}
