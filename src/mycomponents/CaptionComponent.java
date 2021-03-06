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

/**
 *
 * @author User
 */
public class CaptionComponent extends JPanel{
    private JLabel jLabelCaption;

    public CaptionComponent(String caption, int widthPanel, int heightPanel) {
        initComponent(caption, widthPanel, heightPanel);
    }

    private void initComponent(String caption, int widthPanel, int heightPanel) {
        jLabelCaption = new JLabel(caption);
        jLabelCaption.setPreferredSize(new Dimension(widthPanel, heightPanel));
        setMinimumSize(new Dimension(widthPanel, heightPanel));
        setMaximumSize(getMinimumSize());
        setPreferredSize(getMinimumSize());
        setLayout(new FlowLayout());
        add(jLabelCaption);
        jLabelCaption.setHorizontalAlignment(JLabel.CENTER);
    }
    
}
