/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occult;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author Tønnes-Skole-Pc2
 */
public class Rendering extends JPanel {

    
    public Rendering() {
    }
    
    @Override
    protected void paintComponent(Graphics g){                 
        super.paintComponent(g);

        Main.main.render((Graphics2D) g);
    }
}
