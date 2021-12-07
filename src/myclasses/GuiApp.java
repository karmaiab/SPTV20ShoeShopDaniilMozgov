/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import mycomponents.CaptionComponent;

/**
 *
 * @author User
 */
public class GuiApp extends JFrame{
    
    private CaptionComponent captionComponent;
    
    public GuiApp(){
        initComponents();
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        this.setMinimumSize(new Dimension(600,400));
        this.setPreferredSize(this.getMaximumSize());
        this.setMaximumSize(this.getMaximumSize());
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        captionComponent = new CaptionComponent("Adding new shoes ", this.getWidth(), 50);
        this.getContentPane().add(captionComponent);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
        
    }

    }

