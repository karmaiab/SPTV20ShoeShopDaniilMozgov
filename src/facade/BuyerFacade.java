/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Buyer;

/**
 *
 * @author User
 */
public class BuyerFacade extends AbstractFacade<Buyer> {
    
    public BuyerFacade(Class<Buyer> entityClass) {
        super(entityClass);
    }
    
}
