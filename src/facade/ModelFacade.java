/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Model;

/**
 *
 * @author User
 */
public class ModelFacade extends AbstractFacade<Model> {
    
    public ModelFacade(Class<Model> entityClass) {
        super(entityClass);
    }
    
}
