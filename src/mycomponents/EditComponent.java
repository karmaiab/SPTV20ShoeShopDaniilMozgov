/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycomponents;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class EditComponent extends JPanel{
    private JLabel jLabelTitle;
    private JTextField edit;

    public EditComponent(String title, int widthPanel, int heightPanel) {
        initComponent(title, widthPanel, heightPanel);
    }

    private void initComponent(String title,  int widthPanel, int heightPanel) {
        setMinimumSize(new Dimension(widthPanel, heightPanel));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLayout(new FlowLayout());
        this.jLabelTitle = new JLabel(title);
        jLabelTitle.setMinimumSize(new Dimension(widthPanel/3,27));
        
    }
}
