/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sptv20shoeshopdaniilmozgov;

import myclasses.App;

/**
 *
 * @author User
 */
public class SPTV20ShoeShopDaniilMozgov {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length > 0){
            App.toFile = true;
        }else{
            App.toFile = false;
        }  
        App app = new App();
        app.run();
    }
    
}
